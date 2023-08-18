//package org.mongo.Entity;
//
//
//import io.quarkus.mongodb.panache.PanacheMongoEntity;
//import io.quarkus.mongodb.panache.common.MongoEntity;
//import org.bson.codecs.pojo.annotations.BsonProperty;
//import org.bson.types.ObjectId;
//
//import java.util.Date;
//
//@MongoEntity(collection = "billing_zones_by_distance")
//public class BillingZone extends PanacheMongoEntity {
//
//
//    @BsonProperty("name")
//    public String name;
//    @BsonProperty("zone_type")
//    public String zoneType;
//    @BsonProperty("min_distance")
//    public double minDistance;
//    @BsonProperty("max_distance")
//    public double maxDistance;
//    @BsonProperty("updated_at")
//    public Date updatedAt;
//    @BsonProperty("created_at")
//    public Date createdAt;
//
//    @BsonProperty("account_id")
//    public ObjectId accountId;
//
//    public BillingZone(String name, String zoneType, double minDistance, double maxDistance, Date updatedAt, Date createdAt, ObjectId accountId) {
//        this.name = name;
//        this.zoneType = zoneType;
//        this.minDistance = minDistance;
//        this.maxDistance = maxDistance;
//        this.updatedAt = updatedAt;
//        this.createdAt = createdAt;
//        this.accountId = accountId;
//    }
//
//    public BillingZone() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getZoneType() {
//        return zoneType;
//    }
//
//    public void setZoneType(String zoneType) {
//        this.zoneType = zoneType;
//    }
//
//    public double getMinDistance() {
//        return minDistance;
//    }
//
//    public void setMinDistance(double minDistance) {
//        this.minDistance = minDistance;
//    }
//
//    public double getMaxDistance() {
//        return maxDistance;
//    }
//
//    public void setMaxDistance(double maxDistance) {
//        this.maxDistance = maxDistance;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public ObjectId getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(ObjectId accountId) {
//        this.accountId = accountId;
//    }
//}