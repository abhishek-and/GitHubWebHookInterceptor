package com.github.webhook.interceptor.datamap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Restrictions {

    @JsonProperty("users")
    private String[] users;

    @JsonProperty("teams")
    private String[] teams;

    @JsonProperty("users")
    public String[] getUsers() {
        return users;
    }

    @JsonProperty("users")
    public void setUsers(String[] users) {
        this.users = users;
    }

    @JsonProperty("teams")
    public String[] getTeams() {
        return teams;
    }

    @JsonProperty("teams")
    public void setTeams(String[] teams) {
        this.teams = teams;
    }




}
