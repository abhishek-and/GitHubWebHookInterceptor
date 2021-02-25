package com.github.rest.api.invoker;

import com.github.webhook.interceptor.datamap.BranchProtection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GitHubRestAPIInvoker {

    public String invokeGitHubRestEndpoint(String url, HttpMethod method, HttpEntity requestEntity) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, method, requestEntity, String.class);

        return response.getStatusCode().toString();
    }


}
