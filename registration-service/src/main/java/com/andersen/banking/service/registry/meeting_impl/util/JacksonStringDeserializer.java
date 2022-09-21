package com.andersen.banking.service.registry.meeting_impl.util;

import static com.fasterxml.jackson.core.JsonToken.VALUE_FALSE;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_TRUE;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Set;

public class JacksonStringDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        if (Set.of(VALUE_NUMBER_INT, VALUE_NUMBER_FLOAT, VALUE_FALSE, VALUE_TRUE).contains(jsonParser.getCurrentToken())) {
            throw new IllegalArgumentException();
        }
        return jsonParser.getValueAsString();
    }
}
