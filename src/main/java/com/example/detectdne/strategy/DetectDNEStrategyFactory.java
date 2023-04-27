package com.example.detectdne.strategy;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@Component
public class DetectDNEStrategyFactory {

    private Map<DetectDNEStrategyName, DetectDNEStrategy> detectDNEStrategies;

    public DetectDNEStrategyFactory(Set<DetectDNEStrategy> detectDNEStrategies) {
        this.detectDNEStrategies = new EnumMap<>(DetectDNEStrategyName.class);
        detectDNEStrategies.forEach(detectDNEStrategy ->
                this.detectDNEStrategies.put(detectDNEStrategy.getStrategyName(), detectDNEStrategy));
    }

    public DetectDNEStrategy findStrategy(DetectDNEStrategyName detectDNEStrategyName) {
        return detectDNEStrategies.get(detectDNEStrategyName);
    }
}
