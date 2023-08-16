package org.mongo.Entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.List;

@MongoEntity(collection = "zip_codes")
public class ZipCodes extends PanacheMongoEntity {


    String name;
    @BsonProperty("zone_type")
    String zoneType;
    @BsonProperty("min_charge")
    String minCharge;

    @BsonProperty("zip_codes")
    List<String> zipCodes;


    @BsonProperty("updated_at")
    public Date updatedAt;
    @BsonProperty("created_at")
    public Date createdAt;

    @BsonProperty("zip_codes_to_compare")
    public List<Integer> zipCodesToCompare;

    public ZipCodes(String name, String zoneType, String minCharge, List<String> zipCodes, Date updatedAt, Date createdAt, List<Integer> zipCodesToCompare) {
        this.name = name;
        this.zoneType = zoneType;
        this.minCharge = minCharge;
        this.zipCodes = zipCodes;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.zipCodesToCompare = zipCodesToCompare;
    }

    public ZipCodes() {
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

    public List<Integer> getZipCodesToCompare() {
        return zipCodesToCompare;
    }

    public void setZipCodesToCompare(List<Integer> zipCodesToCompare) {
        this.zipCodesToCompare = zipCodesToCompare;
    }
}