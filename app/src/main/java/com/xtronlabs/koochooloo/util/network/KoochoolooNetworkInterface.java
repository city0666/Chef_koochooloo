package com.xtronlabs.koochooloo.util.network;


import com.xtronlabs.koochooloo.util.network.response_models.Country;
import com.xtronlabs.koochooloo.util.network.response_models.Fact;
import com.xtronlabs.koochooloo.util.network.response_models.Ingredient;
import com.xtronlabs.koochooloo.util.network.response_models.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface KoochoolooNetworkInterface {

    @GET("countries")
    Call<Country[]> getCountries();

    @GET("countries/{id}/facts?lang=en")
    Call<Fact[]> getFactForCountry(@Path("id") int id);

    @GET("countries/{id}/recipes?lang=en")
    Call<Recipe[]> getRecipesForCountry(@Path("id") int id);

    @GET("recipes?lang=en")
    Call<Recipe[]> getRecipies();

    @GET("recipes/{id}?lang=en")
    Call<Recipe[]> getRecipeDetailsFor(@Path("id") int id);

    @GET("ingredients/?lang=en")
    Call<Ingredient[]> getIngredients();

    @GET("ingredients/{id}?lang=en")
    Call<Ingredient[]> getIngredientAndTips(@Path("id") int id);
}
