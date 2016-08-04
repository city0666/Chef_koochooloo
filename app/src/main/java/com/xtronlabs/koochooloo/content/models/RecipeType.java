package com.xtronlabs.koochooloo.content.models;


public final class RecipeType extends ModelBase {

    //CREATE TABLE ZRECIPETYPE ( Z_PK INTEGER PRIMARY KEY, Z_ENT INTEGER, Z_OPT INTEGER, ZNAME VARCHAR )

    public static final String RECIPETYPE = "ZRECIPETYPE";
    public static final String NAME = "ZNAME";

    private int recipeType;

    private String name;

    public int getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(int recipeType) {
        this.recipeType = recipeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
