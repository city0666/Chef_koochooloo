package com.xtronlabs.koochooloo.util.network.response_models;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonPropertyOrder({
        "id",
        "name",
        "time",
        "presentation",
        "ingredients",
        "images",
        "steps"
})
public class Recipe {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("time")
    public String time;
    @JsonProperty("presentation")
    public String presentation;
    @JsonProperty("ingredients")
    public List<Ingredient> ingredients = new ArrayList<Ingredient>();
    @JsonProperty("images")
    public List<RecipeImage> images = new ArrayList<RecipeImage>();
    @JsonProperty("steps")
    public List<Step> steps = new ArrayList<Step>();

}