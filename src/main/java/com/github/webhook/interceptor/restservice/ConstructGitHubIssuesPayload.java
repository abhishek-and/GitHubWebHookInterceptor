package com.github.webhook.interceptor.restservice;

import com.github.webhook.interceptor.datamap.GithubIssues;

public class ConstructGitHubIssuesPayload {

    public GithubIssues constructGitHubIssues(String user, String messageBody) {

        GithubIssues ghIssues = new GithubIssues();
        ghIssues.setTitle("Branch Protection Created");
        ghIssues.setBody("@"+user+" Branch Protection created for master branch with the following rules :: \n" + messageBody);

        return ghIssues;
    }
}
