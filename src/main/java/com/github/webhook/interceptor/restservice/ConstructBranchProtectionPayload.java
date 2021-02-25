package com.github.webhook.interceptor.restservice;

import com.github.webhook.interceptor.datamap.BranchProtection;
import com.github.webhook.interceptor.datamap.RequiredPullRequestReviews;
import com.github.webhook.interceptor.datamap.RequiredStatusChecks;
import com.github.webhook.interceptor.datamap.Restrictions;

public class ConstructBranchProtectionPayload {

    public BranchProtection constructBPPayload() {

        BranchProtection branchProtection = new BranchProtection();
        branchProtection.setEnforce_admins(true);

        RequiredStatusChecks statusChecks = new RequiredStatusChecks();
        statusChecks.setStrict(true);
        statusChecks.setContexts(new String[] {"contexts"});

        Restrictions restrictions = new Restrictions();
        restrictions.setUsers(new String[] {});
        restrictions.setTeams(new String[] {});

        branchProtection.setRequired_status_checks(statusChecks);
        branchProtection.setRequired_pull_request_reviews(new RequiredPullRequestReviews());
        branchProtection.setRestrictions(restrictions);
        return branchProtection;
    }
}
