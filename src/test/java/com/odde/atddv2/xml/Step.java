package com.odde.atddv2.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Step {

    @JacksonXmlProperty(isAttribute = true)
    private int index;

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Value")
    private List<Value> values = new ArrayList<>();
}
