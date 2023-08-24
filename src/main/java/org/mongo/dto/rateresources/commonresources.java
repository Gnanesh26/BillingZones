package org.mongo.dto.rateresources;

import org.mongo.common.Enums;

public class commonresources {


    private Enums.CommonResource commonResource;
    private  int value;

    public commonresources() {
    }

    public commonresources(Enums.CommonResource commonResource, int value) {
        this.commonResource = commonResource;
        this.value = value;
    }

    public Enums.CommonResource getCommonResource() {
        return commonResource;
    }

    public void setCommonResource(Enums.CommonResource commonResource) {
        this.commonResource = commonResource;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
