package org.mongo.common;

public class Enums {
    public enum ResourcesType {
        FLIGHTS(0), STAIRS(1), PEOPLE(2), ITEMS(3), ATTEMPTS(4), DISTANCE(5), PALLETS(6),
        ORDERITEMS(7), STORAGE(8), NONE(9);
        private int value;

        ResourcesType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum RateSourceType {
        WEIGHT(0), RANGE(1), TIME(2), FLAT(3), TRANSPORATIONCHARGES(4), PASSTHROUGHCHARGES(5), NONE(6);
        private int value;

        RateSourceType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum VisisbleTo {
        CONSIGNEE(0), DRIVER(1);

        private int value;

        VisisbleTo(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public enum HybridSource {
        SLOTS(0), NONE(1);
        private int value;

        HybridSource(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public enum ChargeItemType {
        ORDER_OR_SHIPMENT(0), PALLET(1), ITEM_OR_MACHINE(2);

        private int value;

        ChargeItemType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public enum ChargableType {

        CALENDER_DAYS(0), BUSINESS_DAYS(1), HOURS(2);
        private int value;

        ChargableType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public enum RoundOffDays {
        WEEKLY(0), BY_MONTH(1), MONTHLY(2), NONE(3);


        private int value;

        RoundOffDays(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum TripType {
        SINGLE_WAY(0), ROUND_TRIP(1);
        private int value;

        TripType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }


    }

    public enum ChargeType {
        PERCENTAGE(0), FLAT(1);


        private int value;

        ChargeType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public enum CommonResource {
        FLIGHTS(0), STAIRS(1), PEOPLE(2), ITEMS(3), ATTEMPTS(4), PALLETS(5), ORDER_ITEMS(6);
        private int value;

        CommonResource(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
