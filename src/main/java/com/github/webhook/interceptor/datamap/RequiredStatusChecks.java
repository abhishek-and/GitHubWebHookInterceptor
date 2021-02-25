package com.github.webhook.interceptor.datamap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequiredStatusChecks {

    @JsonProperty("strict")
    private boolean strict;

    @JsonProperty("contexts")
    private String[] contexts;

    @JsonProperty("strict")
    public boolean isStrict() {
        return strict;
    }

    @JsonProperty("strict")
    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    @JsonProperty("contexts")
    public String[] getContexts() {
        return contexts;
    }

    @JsonProperty("contexts")
    public void setContexts(String[] contexts) {
        this.contexts = contexts;
    }

}
