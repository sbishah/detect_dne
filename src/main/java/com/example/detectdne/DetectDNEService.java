package com.example.detectdne;

import com.example.detectdne.strategy.DetectDNEStrategyFactory;
import com.example.detectdne.strategy.DetectDNEStrategyName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DetectDNEService {

    private static final Logger logger = LogManager.getLogger(DetectDNEService.class);

    private final DetectDNEStrategyFactory detectDNEStrategyFactory;

    public DetectDNEService(DetectDNEStrategyFactory detectDNEStrategyFactory) {
        this.detectDNEStrategyFactory = detectDNEStrategyFactory;
    }

    public boolean detectDNE(DetectDNEStrategyName detectDNEStrategyName, int[] seq) {

        logger.info("Starting DNE detection using strategy " + detectDNEStrategyName + "...");
        long startTime = System.nanoTime();
        boolean ret = detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq);
        long endTime = System.nanoTime();
        float timeInMilliseconds = (float) (endTime - startTime) / 1000000;
        logger.info("Completed DNE detection in " + timeInMilliseconds + " ms");

        return ret;
    }
}
