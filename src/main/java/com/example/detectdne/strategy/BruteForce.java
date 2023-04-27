package com.example.detectdne.strategy;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BruteForce implements DetectDNEStrategy {

    @Override
    public boolean detectDNE(List<Integer> seq) {
        int size = seq.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size - 2; j++) {
                for (int k = j + 1; k < size - 1; k++) {
                    if (seq.get(i) < seq.get(k) && seq.get(k) < seq.get(j)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public DetectDNEStrategyName getStrategyName() {
        return DetectDNEStrategyName.BruteForce;
    }
}
