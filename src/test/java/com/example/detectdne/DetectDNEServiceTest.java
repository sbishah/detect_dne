package com.example.detectdne;

import com.example.detectdne.strategy.DetectDNEStrategyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {DetectDNEService.class, DetectDNEStrategyFactory.class})
public class DetectDNEServiceTest {

    @Test
    void contextLoadsTest() {
    }
}
