package com.xtronlabs.koochooloo.util.network.response_models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "name"
})
public class Language {

    @JsonProperty("code")
    public String code;
    @JsonProperty("name")
    public String name;

}
