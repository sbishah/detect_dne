package com.example.detectdne;

import com.example.detectdne.strategy.DetectDNEStrategyName;
import com.example.detectdne.util.SeqDataSchemaUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
public class DetectDNEController {

    private static final Logger logger = LogManager.getLogger(DetectDNEController.class);

    // constants for the detection results outputs in correlation
    private static final String TRUE_RESPONSE = "true";
    private static final String FALSE_RESPONSE = "false";

    private final DetectDNEService detectDNEService;
    private final SeqDataSchemaUtil seqDataSchemaUtil;

    public DetectDNEController(DetectDNEService detectDNEService, SeqDataSchemaUtil seqDataSchemaUtil) {
        this.detectDNEService = detectDNEService;
        this.seqDataSchemaUtil = seqDataSchemaUtil;
    }

    @PostMapping(value = "${detect_dne.serverMapping}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> server(
            // optional strategy param to choose the DNE detection strategy
            @RequestParam(name = "strategy", defaultValue = "${detect_dne.defaultStrategy}", required = false)
            DetectDNEStrategyName detectDNEStrategyName,
            // the sequence data itself
            @RequestBody JsonNode seqDataNode) {

        logger.info("Received request with sequence data: '" + seqDataNode + "'");
        int[] seq = seqDataSchemaUtil.validateAndParse(seqDataNode);
        if (seq == null) {
            logger.warn("Sequence data is invalid, parsing failed");
            return ResponseEntity.badRequest().body("request has invalid sequence data");
        }

        logger.info("Processing request...");
        boolean detected = detectDNEService.detectDNE(detectDNEStrategyName, seq);
        String response = detected ? TRUE_RESPONSE : FALSE_RESPONSE;
        logger.info("Responding with '" + response + "'");

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException e) {

        logger.warn("Received invalid request:\n" + e.getMessage());

        return ResponseEntity.badRequest().body("request is invalid");
    }
}
