package com.github.webhook.interceptor.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rest.api.invoker.GitHubRestAPIInvoker;
import com.github.webhook.interceptor.datamap.BranchProtection;
import com.github.webhook.interceptor.datamap.CreateEvent;
import com.github.webhook.interceptor.datamap.GithubIssues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
public class WebhookController {

    private static final String SIGNATURE = "X-Hub-Signature";
    private static final String BRANCH_PROTECTION_URL = "/branches/master/protection";
    private static final String BRANCH_PROTECTION_ACCEPT_HEADER = "application/vnd.github.luke-cage-preview+json";
    private static final String WILDCARD_ACCEPT_HEADER = "*/*";


    @Value("${access.token}")
    private String accessToken;

    Logger log = LoggerFactory.getLogger(WebhookController.class);

    @PostMapping("/post")
    public String consumeGitWebhook(@RequestBody String body, @RequestHeader(value = SIGNATURE) String signature) {

        log.info("Received Message:: "+body);

        ObjectMapper oMapper = new ObjectMapper();
        oMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            CreateEvent event = oMapper.readValue(body, CreateEvent.class);
            String action = event.getAction();

            if (action.equalsIgnoreCase("created")) {
                log.info("Action is :: " +action);
                log.info("URL is :: " +event.getRepository().getUrl());
                log.info("Sender Login :: "+ event.getSender().getLogin());

                ConstructBranchProtectionPayload constructBPPayload = new ConstructBranchProtectionPayload();
                BranchProtection branchProtection = constructBPPayload.constructBPPayload();

                log.info("\n BranchProtection Payload :: " +new ObjectMapper().writeValueAsString(branchProtection));

                GitHubRestAPIInvoker apiInvoker = new GitHubRestAPIInvoker();

                //Invoke Github rest api for branch protection
                String statusCode = apiInvoker.invokeGitHubRestEndpoint(event.getRepository().getUrl()+BRANCH_PROTECTION_URL, HttpMethod.PUT,
                        constructRequestEntity(BRANCH_PROTECTION_ACCEPT_HEADER, branchProtection));
                log.info("Status code from Protection API :: " + statusCode);

                ConstructGitHubIssuesPayload ghIssuesConstructor = new ConstructGitHubIssuesPayload();
                GithubIssues issuePayload = ghIssuesConstructor.constructGitHubIssues(event.getSender().getLogin(), new ObjectMapper().writeValueAsString(branchProtection));

                //Invoke Github rest api for issue creation
                String issuesURL = event.getRepository().getIssues_url();
                String formattedIssuesURL = issuesURL.replace("{/number}", "");
                log.info("Issues Creation URL :: " + formattedIssuesURL);
                statusCode = apiInvoker.invokeGitHubRestEndpoint(formattedIssuesURL, HttpMethod.POST,
                        constructRequestEntity(WILDCARD_ACCEPT_HEADER, issuePayload));
                log.info("Status code from GitHub Issues API :: " + statusCode);
            }
            else {
                log.info("Action is not Create hence ignoring webhook notification call :: Action Received :: " +action);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    return HttpStatus.OK.toString();
    }

    private HttpEntity constructRequestEntity(String acceptHeader, Object o) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", acceptHeader);
        HttpEntity requestEntity = new HttpEntity<>(o, headers);
        return requestEntity;
    }

}
