package com.xtronlabs.koochooloo.util.network.response_models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fact",
        "updated_at"
})
public class Fact {

    @JsonProperty("fact")
    private String fact;
    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     *
     * @return
     * The fact
     */
    @JsonProperty("fact")
    public String getFact() {
        return fact;
    }

    /**
     *
     * @param fact
     * The fact
     */
    @JsonProperty("fact")
    public void setFact(String fact) {
        this.fact = fact;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
