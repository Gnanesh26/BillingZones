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


}
