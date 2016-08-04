package com.xtronlabs.koochooloo.content.models;


public final class Ingredient extends ModelBase {

    //Table name
    public static final String INGREDIENT = "ZINGREDIENT";

    //Fields/Columns
    public static final String USER = "ZUSER";
    public static final String NAME= "ZNAME";
    public static final String SPOT_LIGHT = "ZSPOTLIGHT";

    private String user, name, spotLight;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpotLight() {
        return spotLight;
    }

    public void setSpotLight(String spotLight) {
        this.spotLight = spotLight;
    }
}
