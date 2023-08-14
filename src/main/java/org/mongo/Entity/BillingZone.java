package org.mongo.Entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection = "billing_zones_by_distance")
public class BillingZone extends PanacheMongoEntity {


    @BsonProperty("name")
    public String name;

    @BsonProperty("from_distance")
    public double fromDistance;

    @BsonProperty("to_distance")
    public double toDistance;

    public BillingZone(String name, double fromDistance, double toDistance) {
        this.name = name;
        this.fromDistance = fromDistance;
        this.toDistance = toDistance;
    }

    public BillingZone() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFromDistance() {
        return fromDistance;
    }

    public void setFromDistance(double fromDistance) {
        this.fromDistance = fromDistance;
    }

    public double getToDistance() {
        return toDistance;
    }

    public void setToDistance(double toDistance) {
        this.toDistance = toDistance;
    }
}
