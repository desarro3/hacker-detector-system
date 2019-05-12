package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.converter.LogLineConverter;
import com.hotelbeds.supplierintegrations.hackertest.exceptions.DetectorException;
import com.hotelbeds.supplierintegrations.hackertest.models.LogLine;
import com.hotelbeds.supplierintegrations.hackertest.service.DetectorService;
import org.springframework.beans.factory.annotation.Autowired;

import static com.hotelbeds.supplierintegrations.hackertest.config.DetectorConfig.getDefaultConfig;

public class DetectorSystem implements HackerDetector {

    private final DetectorService detectorService;

    private int loginAttemptsFailed;
    private int loginIntervalFailed;

    @Autowired
    public DetectorSystem(DetectorService detectorService) {
        this.detectorService = detectorService;
    }

    public String parseLine(String line) {
        try {
            LogLine logLine;
            LogLineConverter logLineConverter = new LogLineConverter();
            logLine = logLineConverter.decodeLine(line);
            if (logLine.getAction() == LogLine.ActionType.SIGNIN_FAILURE){
                detectorService.add(logLine.getIp(), logLine.getLogDate());
                if (detectorService.exist(logLine.getIp(), loginAttemptsFailed, loginIntervalFailed)) {
                     return logLine.getIp();
                    }
            } else if ((logLine.getAction() == LogLine.ActionType.SIGNIN_SUCCESS) && (detectorService.exist(logLine.getIp()))){
                detectorService.clearAllLogsIp(logLine.getIp());
            }
        } catch (DetectorException e ){
            throw new DetectorException(e.getMessage());
        }

        return null;
    }

    public void setConfig(){
        loginAttemptsFailed = getDefaultConfig().get("COUNT_OF_LOGIN_FAILED");
        loginIntervalFailed = getDefaultConfig().get("TIME_OF_LOGIN_FAILED");
    }

}
