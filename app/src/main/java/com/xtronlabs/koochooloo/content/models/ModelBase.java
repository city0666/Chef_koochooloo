package com.xtronlabs.koochooloo.content.models;


public abstract class ModelBase {

    public static final String Z_PK = "Z_PK";
    public static final String ENT = "Z_ENT";
    public static final String OPT = "Z_OPT";

    private int id,ent, opt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnt() {
        return ent;
    }

    public void setEnt(int ent) {
        this.ent = ent;
    }

    public int getOpt() {
        return opt;
    }

    public void setOpt(int opt) {
        this.opt = opt;
    }
}
