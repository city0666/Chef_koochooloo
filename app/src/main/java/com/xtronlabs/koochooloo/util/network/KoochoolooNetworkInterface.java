package com.xtronlabs.koochooloo.util.network;


import com.xtronlabs.koochooloo.util.network.response_models.Countries;
import com.xtronlabs.koochooloo.util.network.response_models.Country;
import com.xtronlabs.koochooloo.util.network.response_models.Fact;
import com.xtronlabs.koochooloo.util.network.response_models.Facts;
import com.xtronlabs.koochooloo.util.network.response_models.Ingredient;
import com.xtronlabs.koochooloo.util.network.response_models.Recipe;
import com.xtronlabs.koochooloo.util.network.response_models.RecipeIngredient;
import com.xtronlabs.koochooloo.util.network.response_models.Recipes;
import com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew.RecipeNew;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KoochoolooNetworkInterface {

    String AT = "24df4bb0f01e43f91c55a259fcc01212";

    //@GET("countries?lang=en&access_token=" + AT)
    @GET("countries.php")
    Call<Countries> getCountries();

    //@GET("countries/{id}/facts?lang=en&access_token=" + AT)
    @GET("countriesfacts.php")
    Call<Facts> getFactForCountry(@Query("id") int id);

    //@GET("countries/{id}/recipes?lang=en&access_token=" + AT)
    @GET("countriesrecipes.php")
    Call<Recipes> getRecipesForCountry(@Query("id") int id);

    //@GET("recipes?lang=en&access_token=" + AT)
    @GET("recipes.php")
    Call<RecipeIngredient> getRecipies();

    //@GET("recipes/{id}?lang=en&access_token=" + AT)
    @GET("recipesid.php")
    Call<RecipeNew> getRecipeDetailsFor(@Query("id") int id);

    //@GET("recipeIngredientNews/?lang=en&access_token=" + AT)
    @GET("recipeIngredientNews.php")
    Call<Ingredient[]> getIngredients();

    //@GET("recipeIngredientNews/{id}?lang=en&access_token=" + AT)
    @GET("recipeIngredientNews.php")
    Call<Ingredient[]> getIngredientAndTips(@Query("id") int id);
}
