package com.odde.atddv2.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeBody {

    @JacksonXmlElementWrapper(localName = "ParamList")
    @JacksonXmlProperty(localName = "Param")
    private List<Param> paramList;

    @JacksonXmlProperty(localName = "Step")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Step step;
}
