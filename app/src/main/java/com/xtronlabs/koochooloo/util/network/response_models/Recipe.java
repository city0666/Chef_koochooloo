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
        "thumbnail",
        "updated_at"
})
public class Recipe {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("time")
    public String time;
    @JsonProperty("thumbnail")
    public String thumbnail;
    @JsonProperty("updated_at")
    public String updatedAt;

}