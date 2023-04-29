package com.example.detectdne.strategy;

public interface DetectDNEStrategy {

    boolean detectDNE(int[] seq);

    DetectDNEStrategyName getStrategyName();
}
