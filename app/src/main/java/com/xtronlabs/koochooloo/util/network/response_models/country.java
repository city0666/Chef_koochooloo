package com.xtronlabs.koochooloo.util.network.response_models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "name",
        "capital",
        "population",
        "national_dish",
        "country_image",
        "country_flag"
})
public class Country {

    @JsonProperty("code")
    public String code;
    @JsonProperty("name")
    public String name;
    @JsonProperty("capital")
    public String capital;
    @JsonProperty("population")
    public Integer population;
    @JsonProperty("national_dish")
    public String nationalDish;
    @JsonProperty("country_image")
    public Object countryImage;
    @JsonProperty("country_flag")
    public Object countryFlag;

}

