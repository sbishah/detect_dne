package com.example.detectdne.strategy;

// the DNE detection algorithm strategy interface
public interface DetectDNEStrategy {

    // perform the detection and return the result
    boolean detectDNE(int[] seq);

    // return the strategy name enum
    DetectDNEStrategyName getStrategyName();
}
