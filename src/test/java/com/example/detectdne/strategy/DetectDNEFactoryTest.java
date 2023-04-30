package com.example.detectdne.strategy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {DetectDNEStrategyFactory.class, BruteForce.class, ReverseSearch.class, MinLeftClosestRightSearch.class})
public class DetectDNEFactoryTest {

    @Autowired
    private DetectDNEStrategyFactory detectDNEStrategyFactory;

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    void findAllStrategiesTest(DetectDNEStrategyName detectDNEStrategyName) {
        assertEquals(detectDNEStrategyName, detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).getStrategyName());
    }
}
