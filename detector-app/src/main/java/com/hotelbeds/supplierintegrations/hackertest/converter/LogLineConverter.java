package com.hotelbeds.supplierintegrations.hackertest.converter;

import com.hotelbeds.supplierintegrations.hackertest.exceptions.DetectorException;
import com.hotelbeds.supplierintegrations.hackertest.models.LogLine;

public class LogLineConverter {

    //positions in the log line
    private static final int IP_POSITION = 0;
    private static final int DATE_POSITION = 1;
    private static final int ACTION_POSITION = 2;
    private static final int USERNAME_POSITION = 3;

    public LogLine decodeLine(String line){
        if (line.isEmpty()) {
            throw new DetectorException("Invalid Log Line");
        }

        String[] decodedLine;
        LogLine logLine = new LogLine();

        if (line.contains(",")) {
            try {
                decodedLine = line.split(",");
                if (decodedLine.length != 4){
                    throw new DetectorException("Incompleted Log Line");
                }

                logLine.setIp(decodedLine[IP_POSITION].trim());
                logLine.setLogDate(Long.parseLong(decodedLine[DATE_POSITION].trim())*1000);
                logLine.setAction(LogLine.ActionType.valueOf(decodedLine[ACTION_POSITION].trim()));
                logLine.setUsername(decodedLine[USERNAME_POSITION].trim());

            } catch (NumberFormatException e) {
                throw new DetectorException("epoch date is invalid");
            } catch (IllegalArgumentException e) {
                throw new DetectorException("An error occurred when decoding the log line");
            }

        } else {
            throw new DetectorException("the string " + line + " does not contain commas");
        }

        return logLine;

    }

}
