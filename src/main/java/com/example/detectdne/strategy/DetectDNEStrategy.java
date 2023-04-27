package com.example.detectdne.strategy;

import java.util.List;

public interface DetectDNEStrategy {

    public boolean detectDNE(List<Integer> seq);

    public DetectDNEStrategyName getStrategyName();
}
