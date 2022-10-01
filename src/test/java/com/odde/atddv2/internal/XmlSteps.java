package com.odde.atddv2.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.odde.atddv2.xml.RecipeBody;
import io.cucumber.java.en.Given;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;

public class XmlSteps {

    @Given("read and load ch:")
    public void read_and_load_ch(String content) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        String actual = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xmlMapper.writeValueAsString(xmlMapper.readValue(content, RecipeBody.class));
        assertThat(actual, CompareMatcher.isIdenticalTo(content).ignoreWhitespace().ignoreComments().ignoreElementContentWhitespace());
    }
}
