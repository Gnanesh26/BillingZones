package org.mongo.dto.rateresources;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.mongo.common.Enums;

public class Storage {
    private Enums.ChargeItemType chargeItemType;
    private Enums.ChargableType chargableType;
    private Enums.RoundOffDays roundOffDays;
    @BsonProperty("no_of_business_days")
    private int noOfFreeBusinessDays;
    @BsonProperty("charge_per_business_days")
    private int chargePerBusinessDays;

    public Storage() {
    }

    public Storage(Enums.ChargeItemType chargeItemType, Enums.ChargableType chargableType, Enums.RoundOffDays roundOffDays, int noOfFreeBusinessDays, int chargePerBusinessDays) {
        this.chargeItemType = chargeItemType;
        this.chargableType = chargableType;
        this.roundOffDays = roundOffDays;
        this.noOfFreeBusinessDays = noOfFreeBusinessDays;
        this.chargePerBusinessDays = chargePerBusinessDays;
    }

    public Enums.ChargeItemType getChargeItemType() {
        return chargeItemType;
    }

    public void setChargeItemType(Enums.ChargeItemType chargeItemType) {
        this.chargeItemType = chargeItemType;
    }

    public Enums.ChargableType getChargableType() {
        return chargableType;
    }

    public void setChargableType(Enums.ChargableType chargableType) {
        this.chargableType = chargableType;
    }

    public Enums.RoundOffDays getRoundOffDays() {
        return roundOffDays;
    }

    public void setRoundOffDays(Enums.RoundOffDays roundOffDays) {
        this.roundOffDays = roundOffDays;
    }

    public int getNoOfFreeBusinessDays() {
        return noOfFreeBusinessDays;
    }

    public void setNoOfFreeBusinessDays(int noOfFreeBusinessDays) {
        this.noOfFreeBusinessDays = noOfFreeBusinessDays;
    }

    public int getChargePerBusinessDays() {
        return chargePerBusinessDays;
    }

    public void setChargePerBusinessDays(int chargePerBusinessDays) {
        this.chargePerBusinessDays = chargePerBusinessDays;
    }
}
