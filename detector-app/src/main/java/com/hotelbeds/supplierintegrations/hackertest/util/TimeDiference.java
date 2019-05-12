package com.hotelbeds.supplierintegrations.hackertest.util;

public class TimeDiference {

    private TimeDiference() {
    }

    public static long getTimeDifference(long dateStart, long dateEnd) {
        return dateEnd - dateStart;
    }
}
