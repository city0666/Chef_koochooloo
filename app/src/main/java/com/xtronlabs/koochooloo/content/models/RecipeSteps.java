package com.xtronlabs.koochooloo.content.models;


public final class RecipeSteps extends ModelBase{


    //Table name
    public static final String RECIPESTEP ="ZRECIPESTEP";

    //Fields/colums
    public static final String ORDER ="ZORDER";
    public static final String TYPE ="ZTYPE";
    public static final String RECIPE = "ZRECIPE";
    public static final String LABEL ="ZLABEL";

    private int order,type,recipe;
    private String label;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
