package org.mongo.Request;

import org.bson.types.ObjectId;
import org.mongo.common.Enums;

public class AccessorialsRequest {
    private String name;
    private String code;
    private Enums.VisisbleTo visibleTo;
    private Enums.RateSourceType rateSource;
    private Enums.ResourcesType resources;
    private Enums.HybridSource hybridSource;
    private ObjectId accountId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Enums.VisisbleTo getVisibleTo() {
        return visibleTo;
    }

    public void setVisibleTo(Enums.VisisbleTo visibleTo) {
        this.visibleTo = visibleTo;
    }

    public Enums.RateSourceType getRateSource() {
        return rateSource;
    }

    public void setRateSource(Enums.RateSourceType rateSource) {
        this.rateSource = rateSource;
    }

    public Enums.ResourcesType getResources() {
        return resources;
    }

    public void setResources(Enums.ResourcesType resources) {
        this.resources = resources;
    }

    public Enums.HybridSource getHybridSource() {
        return hybridSource;
    }

    public void setHybridSource(Enums.HybridSource hybridSource) {
        this.hybridSource = hybridSource;
    }

    public ObjectId getAccountId() {
        return accountId;
    }

    public void setAccountId(ObjectId accountId) {
        this.accountId = accountId;
    }
}

