package com.xtronlabs.koochooloo.util.network.response_models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "name",
        "capital",
        "population",
        "national_dish",
        "country_image",
        "country_flag",
        "languages",
        "audios"
})
public class CountryDetails {

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
    public String countryImage;
    @JsonProperty("country_flag")
    public Object countryFlag;
    @JsonProperty("languages")
    public List<Language> languages = new ArrayList<Language>();
    @JsonProperty("audios")
    public List<Object> audios = new ArrayList<Object>();

}
