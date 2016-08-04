package com.xtronlabs.koochooloo.content.models;


public final class RecipeTool extends ModelBase {

    // ZRECIPE INTEGER, ZTOOL INTEGER )

    public static final String RECIPETOOL = "ZRECIPETOOL";
    public static final String RECIPE = "ZRECIPE";
    public static final String TOOL = "ZTOOL";

    private int recipe, tool;


    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public int getTool() {
        return tool;
    }

    public void setTool(int tool) {
        this.tool = tool;
    }
}
