package com.odde.atddv2.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringParam.class, name = "S"),
        @JsonSubTypes.Type(value = IntegerParam.class, name = "I"),
        @JsonSubTypes.Type(value = DoubleParam.class, name = "D")
})
@Getter
@Setter
public class Param {

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private String dataObject;

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Type type;

    enum Type {
        S, I, D
    }
}
