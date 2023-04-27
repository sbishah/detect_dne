package com.example.detectdne;

import com.example.detectdne.strategy.DetectDNEStrategyName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DetectDNEController {

    private static final Logger logger = LogManager.getLogger(DetectDNEController.class);

    // constants for the detection results outputs in correlation
    private static final String TRUE_RESPONSE = "true";
    private static final String FALSE_RESPONSE = "false";

    private final DetectDNEService detectDNEService;

    public DetectDNEController(DetectDNEService detectDNEService) {
        this.detectDNEService = detectDNEService;
    }

    @PostMapping(value = "${detect_dne.serverMapping}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> server(
            // optional strategy param to choose the DNE detection strategy
            @RequestParam(name = "strategy", defaultValue = "${detect_dne.defaultStrategy}", required = false)
            DetectDNEStrategyName detectDNEStrategyName,
            // the sequence data itself, automatically parse the JSON
            @RequestBody Map.Entry<String, List<Integer>> seqData) {

        // return a bad request response in case of an invalid sequence data
        if (seqData.getKey() != "seq" || seqData.getValue().isEmpty()) {
            logger.warn("bad request received with data: '" + seqData + "'");
            return ResponseEntity.badRequest().body("invalid sequence data!");
        }

        logger.info("request received with sequence: " + seqData.getValue());
        boolean result = detectDNEService.detectDNE(detectDNEStrategyName, seqData.getValue());

        return ResponseEntity.ok(result ? TRUE_RESPONSE : FALSE_RESPONSE);
    }
}
