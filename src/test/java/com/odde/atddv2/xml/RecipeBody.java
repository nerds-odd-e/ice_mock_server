package com.odde.atddv2.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeBody {

    @JacksonXmlProperty(localName = "ParamList")
    private List<Param> paramList;
}
