package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.config.DetectorConfig;
import com.hotelbeds.supplierintegrations.hackertest.repository.DetectorRepository;
import com.hotelbeds.supplierintegrations.hackertest.service.DetectorService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

public class DetectorSystemTest {
    private List<String> loglines = new ArrayList();

    private final DetectorRepository logLineRepository = mock(DetectorRepository.class);
    private final DetectorService detectorService = new DetectorService(logLineRepository);
    private final DetectorSystem detectorSystem = new DetectorSystem(detectorService);

    @Test
    public void shouldGetDefaultConfig(){
        Map<String, Integer> map = DetectorConfig.getDefaultConfig();
        assertThat(map.get("COUNT_OF_LOGIN_FAILED"), is(5));

        assertThat(map.get("TIME_OF_LOGIN_FAILED"), is(300000));
    }

    @Test
    public void shouldNumberOfUnsuccessfulLoginAttempts(){
       detectorSystem.setConfig();

       String result = detectorSystem.parseLine("80.238.9.179,1557350569,SIGNIN_SUCCESS,Will.Smith");
       assertThat(result, is(anyString()));

    }
}
