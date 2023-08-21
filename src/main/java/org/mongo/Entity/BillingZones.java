package org.mongo.Entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
@MongoEntity(collection = "billing_zones")
public class BillingZones extends PanacheMongoEntity {

        @BsonProperty("name")
        private String name;
        @BsonProperty("zone_type")
        private String zoneType;
        @BsonProperty("updated_at")
        private Date updatedAt;
        @BsonProperty("created_at")
        private Date createdAt;

        // for distance
        @BsonProperty("min_distance")
        private double minDistance;
        @BsonProperty("max_distance")
        private double maxDistance;
        @BsonProperty("account_id")
        private ObjectId accountId;

        // for zipCodes
        @BsonProperty("min_charge")
        private String minCharge;
        @BsonProperty("zip_codes")
        private List<String> zipCodes;
        @BsonProperty("zip_codes_to_compare")
        private List<Integer> zipCodesToCompare;

    public BillingZones(String name, String zoneType, Date updatedAt, Date createdAt, double minDistance, double maxDistance, ObjectId accountId, String minCharge, List<String> zipCodes, List<Integer> zipCodesToCompare) {
        this.name = name;
        this.zoneType = zoneType;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.accountId = accountId;
        this.minCharge = minCharge;
        this.zipCodes = zipCodes;
        this.zipCodesToCompare = zipCodesToCompare;
    }

    public BillingZones() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public ObjectId getAccountId() {
        return accountId;
    }

    public void setAccountId(ObjectId accountId) {
        this.accountId = accountId;
    }

    public String getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(String minCharge) {
        this.minCharge = minCharge;
    }

    public List<String> getZipCodes() {
        return zipCodes;
    }

    public void setZipCodes(List<String> zipCodes) {
        this.zipCodes = zipCodes;
    }

    public List<Integer> getZipCodesToCompare() {
        return zipCodesToCompare;
    }

    public void setZipCodesToCompare(List<Integer> zipCodesToCompare) {
        this.zipCodesToCompare = zipCodesToCompare;
    }



}
