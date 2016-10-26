package com.xtronlabs.koochooloo.content.models;


public final class Metadata extends ModelBase {

    //CREATE TABLE Z_METADATA (Z_VERSION INTEGER PRIMARY KEY, Z_UUID VARCHAR(255), Z_PLIST BLOB)

    public static final String METADATA = "Z_METADATA";
    public static final String VERSION = "ZNAME";
    public static final String UUID = "ZNAME";
    public static final String PLIST = "ZNAME";

    private int version, uuid, plist;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public int getPlist() {
        return plist;
    }

    public void setPlist(int plist) {
        this.plist = plist;
    }
}
