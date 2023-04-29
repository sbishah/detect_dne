package com.example.detectdne.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

// the sequence data JSON schema to validate and parse
@Service
public class SeqDataSchemaUtil {

    private static final Logger logger = LogManager.getLogger(SeqDataSchemaUtil.class);

    private final String seqDataFieldName;
    private final JsonSchema seqDataSchema;

    public SeqDataSchemaUtil(@Value("${detect_dne.seqData.fieldName}") String seqDataFieldName,
                             @Value("${detect_dne.seqData.schema}") String seqDataSchema) {
        this.seqDataFieldName = seqDataFieldName;
        this.seqDataSchema = JsonSchemaFactory
                .getInstance(SpecVersion.VersionFlag.V201909)
                .getSchema(seqDataSchema);
    }

    public int[] validateAndParse(JsonNode seqDataNode) {

        logger.info("Validating sequence data...");
        // try parsing the sequence data
        Set<ValidationMessage> errors = seqDataSchema.validate(seqDataNode);
        if (!errors.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            errors.forEach(validationMessage -> stringBuilder.append("\n" + validationMessage));
            logger.warn("Found " + errors.size() + " errors in validation:" + stringBuilder);
            return null;
        }

        logger.info("Parsing sequence data...");
        JsonNode seqNode = seqDataNode.get(seqDataFieldName);
        int size = seqNode.size();
        int[] seq = new int[size];
        Iterator<JsonNode> iterator = seqNode.iterator();
        for (int i = 0; i < seqNode.size() && iterator.hasNext(); i++) {
            seq[i] = iterator.next().asInt();
        }

        return seq;
    }
}
