package com.hotelbeds.supplierintegrations.hackertest.service;

import com.hotelbeds.supplierintegrations.hackertest.exceptions.LineLogNotFoundException;
import com.hotelbeds.supplierintegrations.hackertest.repository.DetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.hotelbeds.supplierintegrations.hackertest.util.TimeDiference.getTimeDifference;

public class DetectorService {

    private DetectorRepository logLineRepository;

    @Autowired
    public DetectorService(DetectorRepository logLineRepository) {
        this.logLineRepository = logLineRepository;
    }

    public void add(String ip, long time){

        logLineRepository.save(ip, time);
    }

    public boolean exist(String ip) {

        return logLineRepository.exists(ip);
    }

    public boolean exist(String ip, int count, long interval) {
        try {
            List<Long> logLineTimes = logLineRepository.getById(ip);

            if (logLineTimes.size() < count) {
                return false;
            }

            long mostRecentTimeofLog = logLineTimes.get(logLineTimes.size()-1);
            long initialTimeOfLog = logLineTimes.get(logLineTimes.size() - count);
            long logLineInterval = getTimeDifference(mostRecentTimeofLog, initialTimeOfLog);

            if (logLineInterval < interval) {
                return true;
            }
        }
        catch (LineLogNotFoundException e) {
            throw new LineLogNotFoundException("The ip address does not exists");
        }

        return false;
    }

    public void clearAllLogsIp(String ip) {
        logLineRepository.removeById(ip);
    }

    public void clearAllLogs(){
        logLineRepository.removeAll();
    }

}
