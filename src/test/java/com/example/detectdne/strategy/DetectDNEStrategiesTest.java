package com.example.detectdne.strategy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {DetectDNEStrategyFactory.class, BruteForce.class, ReverseSearch.class, MinLeftClosestRightSearch.class})
public class DetectDNEStrategiesTest {

    @Autowired
    private DetectDNEStrategyFactory detectDNEStrategyFactory;

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 1: No DNE subsequence
    public void falseSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {1, 2, 3, 7};

        assertFalse(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 2: DNE subsequence present
    public void trueSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {4, 1, 7, 2};

        assertTrue(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 3: DNE subsequence present with duplicate elements
    public void duplicateElementsSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {1, 2, 2, 4, 3};

        assertTrue(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 4: DNE subsequence not possible due to the order
    public void badOrderSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {5, 4, 3, 2, 1};

        assertFalse(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 5: DNE subsequence present with negative numbers
    public void negativeTrueSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {-2, -3, -1, -2};

        assertTrue(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 6: No DNE subsequence with negative numbers
    public void negativeFalseSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {-2, -3, -4, -5};

        assertFalse(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 7: Empty sequence
    public void emptySequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {};

        assertFalse(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 8: Single element sequence
    public void singleElementSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {1};

        assertFalse(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 9: Two element sequence
    public void twoElementsSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {1, 2};

        assertFalse(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 10: DNE subsequence at the beginning
    public void dneAtTheBeginningSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {1, 3, 2, 4, 5};

        assertTrue(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }

    @ParameterizedTest
    @EnumSource(DetectDNEStrategyName.class)
    // Test Case 11: DNE subsequence at the end
    public void dneAtTheEndSequenceTest(DetectDNEStrategyName detectDNEStrategyName) {
        int[] seq = {1, 2, 3, 5, 4};

        assertTrue(detectDNEStrategyFactory.findStrategy(detectDNEStrategyName).detectDNE(seq));
    }
}
