package org.mongo.dto.rateresources;

import org.mongo.common.Enums;

public class Distance {

    private int freeMiles;
    private int durationMiles;
    private Enums.TripType tripType;

    public Distance() {
    }

    public Distance(int freeMiles, int durationMiles, Enums.TripType tripType) {
        this.freeMiles = freeMiles;
        this.durationMiles = durationMiles;
        this.tripType = tripType;
    }

    public int getFreeMiles() {
        return freeMiles;
    }

    public void setFreeMiles(int freeMiles) {
        this.freeMiles = freeMiles;
    }

    public int getDurationMiles() {
        return durationMiles;
    }

    public void setDurationMiles(int durationMiles) {
        this.durationMiles = durationMiles;
    }

    public Enums.TripType getTripType() {
        return tripType;
    }

    public void setTripType(Enums.TripType tripType) {
        this.tripType = tripType;
    }
}
