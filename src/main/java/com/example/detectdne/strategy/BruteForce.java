package com.example.detectdne.strategy;

import org.springframework.stereotype.Component;

// time Complexity of O(N^3)
// extra space complexity of O(1)
@Component
public class BruteForce implements DetectDNEStrategy {

    @Override
    public boolean detectDNE(int[] seq) {
        for (int i = 0; i < seq.length; i++) {
            for (int j = i + 1; j < seq.length - 2; j++) {
                for (int k = j + 1; k < seq.length - 1; k++) {
                    if (seq[i] < seq[k] && seq[k] < seq[j]) {
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
