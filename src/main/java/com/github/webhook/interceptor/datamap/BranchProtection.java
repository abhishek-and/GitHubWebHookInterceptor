package com.github.webhook.interceptor.datamap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchProtection {

    @JsonProperty("required_status_checks")
    private RequiredStatusChecks required_status_checks;

    @JsonProperty("enforce_admins")
    private boolean enforce_admins;

    @JsonProperty("required_pull_request_reviews")
    private RequiredPullRequestReviews required_pull_request_reviews;

    @JsonProperty("restrictions")
    private Restrictions restrictions;

    @JsonProperty("required_status_checks")
    public RequiredStatusChecks getRequired_status_checks() {
        return required_status_checks;
    }

    @JsonProperty("required_status_checks")
    public void setRequired_status_checks(RequiredStatusChecks required_status_checks) {
        this.required_status_checks = required_status_checks;
    }

    @JsonProperty("enforce_admins")
    public boolean isEnforce_admins() {
        return enforce_admins;
    }

    @JsonProperty("enforce_admins")
    public void setEnforce_admins(boolean enforce_admins) {
        this.enforce_admins = enforce_admins;
    }

    @JsonProperty("required_pull_request_reviews")
    public RequiredPullRequestReviews getRequired_pull_request_reviews() {
        return required_pull_request_reviews;
    }

    @JsonProperty("required_pull_request_reviews")
    public void setRequired_pull_request_reviews(RequiredPullRequestReviews required_pull_request_reviews) {
        this.required_pull_request_reviews = required_pull_request_reviews;
    }

    @JsonProperty("restrictions")
    public Restrictions getRestrictions() {
        return restrictions;
    }

    @JsonProperty("restrictions")
    public void setRestrictions(Restrictions restrictions) {
        this.restrictions = restrictions;
    }

}
