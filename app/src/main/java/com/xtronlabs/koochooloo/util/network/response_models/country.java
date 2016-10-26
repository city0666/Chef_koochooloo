package com.xtronlabs.koochooloo.util.network.response_models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "code",
        "name",
        "capital",
        "population",
        "national_dish",
        "country_image",
        "country_flag",
        "updated_at"
})
public class Country {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("code")
    public String code;
    @JsonProperty("name")
    public String name;
    @JsonProperty("capital")
    public String capital;
    @JsonProperty("population")
    public Integer population;
    @JsonProperty("national_dish")
    public Object nationalDish;
    @JsonProperty("country_image")
    public String countryImage;
    @JsonProperty("country_flag")
    public String countryFlag;
    @JsonProperty("updated_at")
    public String updated_at;

}

