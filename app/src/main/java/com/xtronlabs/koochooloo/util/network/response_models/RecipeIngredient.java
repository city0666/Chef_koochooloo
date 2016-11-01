package com.xtronlabs.koochooloo.util.network.response_models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "recipeIngredientNews"
})
public class RecipeIngredient {

    @JsonProperty("recipeIngredientNews")
    public List<Ingredient_R> ingredients = new ArrayList<>();

}
