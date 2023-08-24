package org.mongo.dto.hybridsource;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.mongo.common.Enums;

import java.time.LocalTime;

public class Slots {
    @BsonProperty("from_time")
    private LocalTime fromTime;
    @BsonProperty("to_time")
    private LocalTime toTime;

    private Enums.ChargeType chargeType;
    private double chargeValue;

    public Slots() {
    }

    public Slots(LocalTime fromTime, LocalTime toTime, Enums.ChargeType chargeType, double chargeValue) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.chargeType = chargeType;
        this.chargeValue = chargeValue;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public Enums.ChargeType getChargeType() {
        return chargeType;
    }

    public void setChargeType(Enums.ChargeType chargeType) {
        this.chargeType = chargeType;
    }

    public double getChargeValue() {
        return chargeValue;
    }

    public void setChargeValue(double chargeValue) {
        this.chargeValue = chargeValue;
    }
}
