package com.xtronlabs.koochooloo.util.network.response_models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "facts"
})
public class Facts {
    @JsonProperty("facts")
    private List<Fact> facts = new ArrayList<Fact>();

    /**
     *
     * @return
     * The facts
     */
    @JsonProperty("facts")
    public List<Fact> getFacts() {
        return facts;
    }

    /**
     *
     * @param facts
     * The facts
     */
    @JsonProperty("facts")
    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }

}
