package com.example.detectdne.strategy;

import org.springframework.stereotype.Component;

// time Complexity of O(N^2)
// extra space complexity of O(1)
@Component
public class ReverseSearch implements DetectDNEStrategy {

    @Override
    public boolean detectDNE(int[] seq) {

        int count;
        for (int i = seq.length - 1; i >= 2; i--) {
            count = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (seq[i] < seq[j]) {
                    count++;
                } else if (seq[j] < seq[i] && count > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public DetectDNEStrategyName getStrategyName() {
        return DetectDNEStrategyName.ReverseSearch;
    }
}
