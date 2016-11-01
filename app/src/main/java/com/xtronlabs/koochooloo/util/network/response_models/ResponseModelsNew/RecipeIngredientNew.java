
package com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "tip",
        "measurement_unit",
        "quantity",
        "updated_at"
})
public class RecipeIngredientNew {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("tip")
    public String tip;
    @JsonProperty("measurement_unit")
    public String measurementUnit;
    @JsonProperty("quantity")
    public int quantity;
    @JsonProperty("updated_at")
    public String updatedAt;

}