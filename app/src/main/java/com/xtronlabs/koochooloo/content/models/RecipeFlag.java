package com.xtronlabs.koochooloo.content.models;


public final class RecipeFlag extends ModelBase {

    //Table name
    public static final String RECIPEFLAG="ZRECIPEFLAG";

    //Fields/Columns
    public static final String FLAG = "ZFLAG";
    public static final String RECIPE ="ZRECIPE";


    private int flag, recipe;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }
}
