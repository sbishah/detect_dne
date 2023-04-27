package com.example.detectdne;

import com.example.detectdne.strategy.DetectDNEStrategyFactory;
import com.example.detectdne.strategy.DetectDNEStrategyName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectDNEService {

    private static final Logger logger = LogManager.getLogger(DetectDNEController.class);

    private final DetectDNEStrategyFactory detectDNEStrategyFactory;

    public DetectDNEService(DetectDNEStrategyFactory detectDNEStrategyFactory) {
        this.detectDNEStrategyFactory = detectDNEStrategyFactory;
    }

    public boolean detectDNE(DetectDNEStrategyName detectDNEStrategyName, List<Integer> seq) {

        logger.info("DNE detection started using strategy: " + detectDNEStrategyName);
        boolean ret = detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq);
        logger.info("DNE detection returned: " + ret);

        return ret;
    }
}
