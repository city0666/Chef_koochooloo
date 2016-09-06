package com.xtronlabs.koochooloo.util.network;


import com.xtronlabs.koochooloo.util.network.response_models.Countries;
import com.xtronlabs.koochooloo.util.network.response_models.Country;
import com.xtronlabs.koochooloo.util.network.response_models.Fact;
import com.xtronlabs.koochooloo.util.network.response_models.Facts;
import com.xtronlabs.koochooloo.util.network.response_models.Ingredient;
import com.xtronlabs.koochooloo.util.network.response_models.Recipe;
import com.xtronlabs.koochooloo.util.network.response_models.Recipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface KoochoolooNetworkInterface {

    String AT = "24df4bb0f01e43f91c55a259fcc01212";

    @GET("countries?lang=en&access_token=" + AT)
    Call<Countries> getCountries();

    @GET("countries/{id}/facts?lang=en&access_token=" + AT)
    Call<Facts> getFactForCountry(@Path("id") int id);

    @GET("countries/{id}/recipes?lang=en&access_token=" + AT)
    Call<Recipe[]> getRecipesForCountry(@Path("id") int id);

    @GET("recipes?lang=en&access_token=" + AT)
    Call<Recipes> getRecipies();

    @GET("recipes/{id}?lang=en&access_token=" + AT)
    Call<Recipe[]> getRecipeDetailsFor(@Path("id") int id);

    @GET("ingredients/?lang=en&access_token=" + AT)
    Call<Ingredient[]> getIngredients();

    @GET("ingredients/{id}?lang=en&access_token=" + AT)
    Call<Ingredient[]> getIngredientAndTips(@Path("id") int id);
}
