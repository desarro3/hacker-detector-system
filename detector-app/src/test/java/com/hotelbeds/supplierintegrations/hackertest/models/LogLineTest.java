package com.hotelbeds.supplierintegrations.hackertest.models;

import com.hotelbeds.supplierintegrations.hackertest.converter.LogLineConverter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class LogLineTest {

    @Test
    public void shouldDecodeLine() throws IllegalArgumentException {
        LogLine logLine = new LogLine();
        LogLineConverter logLineConverter = new LogLineConverter();
        logLine = logLineConverter.decodeLine("80.238.9.179,1557350569,SIGNIN_SUCCESS,Will.Smith");

        assertThat(logLine.getAction(), is(LogLine.ActionType.SIGNIN_SUCCESS));

        logLine = logLineConverter.decodeLine("80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith");
        assertThat(logLine.getAction(), is(LogLine.ActionType.SIGNIN_FAILURE));


    }
}
