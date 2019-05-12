package com.hotelbeds.supplierintegrations.hackertest.config;

import java.util.HashMap;
import java.util.Map;

public final class DetectorConfig {

    private static final int NUMBER_LOGIN_FAILED = 5;
    private static final int INTERVAL_LOGIN_FAILED = 300000;

    private static final String COUNT_OF_LOGIN_FAILED = "COUNT_OF_LOGIN_FAILED";
    private static final String TIME_OF_LOGIN_FAILED = "TIME_OF_LOGIN_FAILED";

    static final Map<String, Integer> CONFIG = new HashMap<>();

    private DetectorConfig() {

    }

    public static Map<String, Integer> getDefaultConfig(){
        CONFIG.put(COUNT_OF_LOGIN_FAILED, NUMBER_LOGIN_FAILED);
        CONFIG.put(TIME_OF_LOGIN_FAILED, INTERVAL_LOGIN_FAILED);
        return CONFIG;
    }



}
