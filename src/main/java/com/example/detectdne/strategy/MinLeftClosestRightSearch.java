package com.example.detectdne.strategy;

import org.springframework.stereotype.Component;

import java.util.TreeSet;

// time Complexity of O(N*LOG(N))
// extra space complexity of O(N)
@Component
public class MinLeftClosestRightSearch implements DetectDNEStrategy {

    @Override
    public boolean detectDNE(int[] seq) {

        int[] minLeft = new int[seq.length];

        int currMinLeft = Integer.MAX_VALUE;
        for (int i = 0; i < seq.length; i++) {
            minLeft[i] = currMinLeft;
            if (seq[i] < currMinLeft) {
                currMinLeft = seq[i];
            }
        }

        TreeSet<Integer> sortedSet = new TreeSet<>();

        for (int i = seq.length - 1; i >= 1; i--) {
            Integer floor = sortedSet.floor(seq[i] - 1);
            if (floor != null && minLeft[i] < floor) {
                return true;
            }
            sortedSet.add(seq[i]);
        }

        return false;
    }

    @Override
    public DetectDNEStrategyName getStrategyName() {
        return DetectDNEStrategyName.MinLeftClosestRightSearch;
    }
}
