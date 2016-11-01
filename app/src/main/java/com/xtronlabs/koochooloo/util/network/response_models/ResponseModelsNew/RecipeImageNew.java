
package com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "image"
})
public class RecipeImageNew {

    @JsonProperty("image")
    public String image;

}
