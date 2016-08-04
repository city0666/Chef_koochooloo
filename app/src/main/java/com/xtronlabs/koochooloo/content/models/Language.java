package com.xtronlabs.koochooloo.content.models;


public final class Language extends ModelBase{

    //Table name
    public static final String LANGUAGE = "ZLANGUAGE";

    //Fields/columns
    public static final String MUSIC = "ZMUSIC";
    public static final String CODE = "ZCODE";
    public static final String NAME = "ZNAME";
    public static final String TYPE = "ZTYPE";

    private int music;
    private String code, name, type;

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
