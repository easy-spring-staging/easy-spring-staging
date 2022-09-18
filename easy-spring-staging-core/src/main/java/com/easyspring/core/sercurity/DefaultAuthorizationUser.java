package com.easyspring.core.sercurity;

import java.util.List;

public class DefaultAuthorizationUser implements AuthorizationUser<String, String, String, String>{

    private String accountId;

    private String userName;

    private String account;

    private Boolean admin;

    private List<String> roles;

    private List<String> resourceIds;

    @Override
    public String getAccountId() {
        return this.accountId;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getAccount() {
        return this.account;
    }

    @Override
    public Boolean isAdmin() {
        return this.admin;
    }

    @Override
    public List<String> getRole() {
        return this.roles;
    }

    @Override
    public List<String> getResourceId(String resourceType) {
        return this.resourceIds;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
