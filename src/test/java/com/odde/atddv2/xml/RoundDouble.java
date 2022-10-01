package com.odde.atddv2.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class RoundDouble extends JsonSerializer<Double> {

    private String marshal(Double v) {
        if (v == null)
            return null;
        if (v % 1 == 0)
            return String.valueOf(v.longValue());
        return v.toString();
    }

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(marshal(value));
    }
}
