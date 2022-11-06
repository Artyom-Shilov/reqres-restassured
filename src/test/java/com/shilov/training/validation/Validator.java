package com.shilov.training.validation;

public class Validator {

    public static void checkId(long id, long amount) {
        if (id <= 0 ) {
            throw new IllegalArgumentException("id should be more than 0");
        }
        if (id > amount) {
            throw new IllegalArgumentException("id should be less than current amount of records (" + amount + ")");
        }
    }

    public static void checkIdInRange(long from, long to, long amount) {
        if (from <= 0) {
            throw new IllegalArgumentException("from should be more than 0");
        }
        if (to > amount) {
            throw new IllegalArgumentException(
                    "to should be less than current amount of records (" + amount + ")");
        }
        if (from > to) {
            throw new IllegalArgumentException(
                    "from more than to (" + from + " > " + to + ")");
        }
    }
}
