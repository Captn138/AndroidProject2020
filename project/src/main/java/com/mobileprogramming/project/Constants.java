package com.mobileprogramming.project;

public class Constants {
    private static String KEY_MINECRAFT_LIST = "jsonMinecraftList";
    private static String NAME_SHARED_PREFS = "minecraftList";
    private static String API_URL = "https://minecraft-ids.grahamedgecombe.com/";
    private static String ERROR = "Api Error!";
    private static String TEST = "Elle est où la poulette?";

    public static String getKEY_MINECRAFT_LIST() {
        return KEY_MINECRAFT_LIST;
    }

    public static String getNAME_SHARED_PREFS() {
        return NAME_SHARED_PREFS;
    }

    public static String getAPI_URL() {
        return API_URL;
    }

    public static String getERROR() {
        return ERROR;
    }

    public static String getTEST() { return TEST; }
}
