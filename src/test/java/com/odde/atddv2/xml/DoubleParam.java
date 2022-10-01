package com.odde.atddv2.xml;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleParam extends Param {

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = RoundDouble.class)
    private double min, max, accuracy;

    @JacksonXmlProperty(isAttribute = true)
    private String units;

    @JacksonXmlProperty(isAttribute = true, localName = "default")
    @JsonSerialize(using = RoundDouble.class)
    private double default_;
}
