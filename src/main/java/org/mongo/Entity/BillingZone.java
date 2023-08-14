package org.mongo.Entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection = "BillingZone")
public class BillingZone extends PanacheMongoEntity {


    @BsonProperty("name")
    public String name;

    @BsonProperty("from")
    public double from;

    @BsonProperty("to")
    public double to ;

    public BillingZone(String name, double from, double to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public BillingZone() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }
}
