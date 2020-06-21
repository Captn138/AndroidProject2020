package com.mobileprogramming.project;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobileprogramming.project.Constants;
import com.mobileprogramming.project.data.MinecraftApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static MinecraftApi minecraftApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static MinecraftApi getMinecraftInterface(){
        if(minecraftApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.getAPI_URL())
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            minecraftApiInstance = retrofit.create(MinecraftApi.class);
        }
        return minecraftApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context ctx){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = ctx.getSharedPreferences(Constants.getNAME_SHARED_PREFS(), Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
