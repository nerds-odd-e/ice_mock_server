package com.odde.atddv2.internal;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.odde.atddv2.xml.RecipeBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import org.xmlunit.matchers.CompareMatcher;

import static com.github.leeonky.dal.extension.assertj.DALAssert.expect;
import static org.hamcrest.MatcherAssert.assertThat;

public class XmlSteps {
    private String xml;

    @Given("the follow xml:")
    public void the_follow_xml(String xml) {
        this.xml = xml;
    }

    @SneakyThrows
    @Then("read the following recipe body and do the correct serialization")
    public void read_the_following_recipe_body_and_do_the_correct_serialization(String verification) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        RecipeBody recipeBody = xmlMapper.readValue(xml, RecipeBody.class);

        expect(recipeBody).should(verification);

        String actual = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xmlMapper.writeValueAsString(recipeBody);
        assertThat(actual, CompareMatcher.isIdenticalTo(xml).ignoreWhitespace().ignoreComments().ignoreElementContentWhitespace());
    }
}
