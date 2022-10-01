package com.odde.atddv2.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegerParam extends Param {

    @JacksonXmlProperty(isAttribute = true)
    private int min, max;

    @JacksonXmlProperty(isAttribute = true)
    private String descriptorList, units;

    @JacksonXmlProperty(isAttribute = true, localName = "default")
    private String default_;
}
