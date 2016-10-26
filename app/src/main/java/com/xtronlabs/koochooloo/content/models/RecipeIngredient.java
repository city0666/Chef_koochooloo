package com.xtronlabs.koochooloo.content.models;


public final class RecipeIngredient extends ModelBase{


    //Table name
    public static final String RECIPEINGREDIENT = "ZRECIPEINGREDIENT";

    //Fields/Columns
    public static final String INGREDIENT ="ZINGREDIENT";
    public static final String RECIPE = "ZRECIPE";
    public static final String UNIT ="ZUNIT";
    public static final String AMOUNT ="ZAMOUNT";

    private int ingredient,recipe,unit,amount;

    public int getIngredient() {
        return ingredient;
    }

    public void setIngredient(int ingredient) {
        this.ingredient = ingredient;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
