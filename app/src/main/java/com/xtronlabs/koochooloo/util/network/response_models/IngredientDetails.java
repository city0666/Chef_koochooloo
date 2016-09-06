package com.xtronlabs.koochooloo.util.network.response_models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "tip",
        "updated_at"
})
public class IngredientDetails {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("tip")
    public String tip;
    @JsonProperty("updated_at")
    public String updatedAt;

}
