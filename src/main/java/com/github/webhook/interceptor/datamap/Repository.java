package com.github.webhook.interceptor.datamap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Repository {

    @JsonProperty("url")
    private String url;

    @JsonProperty("issues_url")
    private String issues_url;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("issues_url")
    public String getIssues_url() {
        return issues_url;
    }

    @JsonProperty("issues_url")
    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }

}
