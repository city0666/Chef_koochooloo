package com.xtronlabs.koochooloo.util.network.response_models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "time",
        "presentation",
        "updated_at",
        "recipeIngredientNews",
        "recipeImageNews",
        "recipeStepNews"
})
public class Recipe2 {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("time")
    public String time;
    @JsonProperty("presentation")
    public String presentation;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("recipeIngredientNews")
    public List<Ingredient> ingredients = new ArrayList<Ingredient>();
    @JsonProperty("recipeImageNews")
    public List<Image> images = new ArrayList<Image>();
    @JsonProperty("recipeStepNews")
    public List<Step> steps = new ArrayList<Step>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

