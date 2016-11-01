package com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew;

import java.util.ArrayList;
import java.util.List;
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
        "ingredients",
        "images",
        "steps"
})
public class Recipe_ {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("time")
    public String time;
    @JsonProperty("presentation")
    public String presentation;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("ingredients")
    public List<RecipeIngredientNew> recipeIngredients = new ArrayList<RecipeIngredientNew>();
    @JsonProperty("images")
    public List<RecipeImageNew> recipeImages = new ArrayList<RecipeImageNew>();
    @JsonProperty("steps")
    public List<RecipeStepNew> recipeSteps = new ArrayList<RecipeStepNew>();

}