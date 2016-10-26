package com.xtronlabs.koochooloo.util.network.response_models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "countries"
})
public class Countries {

    @JsonProperty("countries")
    public List<Country> countries = new ArrayList<Country>();

}