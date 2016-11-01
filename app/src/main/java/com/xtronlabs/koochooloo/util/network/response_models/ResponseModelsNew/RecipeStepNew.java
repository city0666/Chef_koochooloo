
package com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "text",
        "updated_at"
})
public class RecipeStepNew implements Parcelable {

    @JsonProperty("type")
    public String type;
    @JsonProperty("text")
    public String text;
    @JsonProperty("updated_at")
    public String updatedAt;

    public RecipeStepNew() {
    }

    @JsonIgnore
    protected RecipeStepNew(Parcel in) {
        type = in.readString();
        text = in.readString();
        updatedAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(text);
        dest.writeString(updatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    public static final Creator<RecipeStepNew> CREATOR = new Creator<RecipeStepNew>() {
        @Override
        public RecipeStepNew createFromParcel(Parcel in) {
            return new RecipeStepNew(in);
        }

        @Override
        public RecipeStepNew[] newArray(int size) {
            return new RecipeStepNew[size];
        }
    };
}
