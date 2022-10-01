package com.odde.atddv2.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value {

    @JacksonXmlProperty(isAttribute = true)
    private String param, value;
}
