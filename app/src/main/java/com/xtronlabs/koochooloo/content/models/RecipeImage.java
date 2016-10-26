package com.xtronlabs.koochooloo.content.models;


public final class RecipeImage extends ModelBase {

    //Table name
    public static final String RECIPEIMAGE = "ZRECIPEIMAGE";

    //Fields/Columns
    public static final String PRESENTATION = "ZPRESENTATION";
    public static final String RECIPE = "ZRECIPE";
    public static final String ALT = "ZALT";
    public static final String URL = "ZURL";


    private int presentation,recipe;
    private String alt, url;

    public int getPresentation() {
        return presentation;
    }

    public void setPresentation(int presentation) {
        this.presentation = presentation;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
