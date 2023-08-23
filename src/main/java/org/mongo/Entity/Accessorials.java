package org.mongo.Entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.mongo.common.Enums;

@MongoEntity(collection = "accessorial")

public class Accessorials extends PanacheMongoEntity {
    @BsonProperty("name")
    private String name;

    @BsonProperty("code")
    private String code;

    @BsonProperty("visible_to")
    private Enums.VisisbleTo visibleto;

    @BsonProperty("Rate_Source")
    private Enums.RateSourceType rateSource;
    @BsonProperty("Resources")
    private Enums.ResourcesType resources;

    @BsonProperty("Hybrid_Source")
    private Enums.HybridSource hybridSource;


    @BsonProperty("account_id")
    private ObjectId accountId;


    public Accessorials(String name, String code, Enums.VisisbleTo visibleto, Enums.RateSourceType rateSource, Enums.ResourcesType resources, Enums.HybridSource hybridSource, ObjectId accountId) {
        this.name = name;
        this.code = code;
        this.visibleto = visibleto;
        this.rateSource = rateSource;
        this.resources = resources;
        this.hybridSource = hybridSource;
        this.accountId = accountId;
    }

    public Accessorials() {
    }

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

    public Enums.VisisbleTo getVisibleto() {
        return visibleto;
    }

    public void setVisibleto(Enums.VisisbleTo visibleto) {
        this.visibleto = visibleto;
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