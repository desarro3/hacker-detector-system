package com.hotelbeds.supplierintegrations.hackertest.models;

import lombok.Data;

@Data
public class LogLine {
    private String ip;
    private long logDate;
    private ActionType action;
    private String username;

    public enum ActionType {
        SIGNIN_SUCCESS, SIGNIN_FAILURE
    }


}
