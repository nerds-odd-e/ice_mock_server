package com.odde.atddv2.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringParam extends Param {

    @JacksonXmlProperty(isAttribute = true)
    private int maxLength;

    @JacksonXmlProperty(isAttribute = true, localName = "default")
    private String default_;
}
