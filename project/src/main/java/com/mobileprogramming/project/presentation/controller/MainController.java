package com.mobileprogramming.project.presentation.controller;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobileprogramming.project.presentation.Constants;
import com.mobileprogramming.project.presentation.Singletons;
import com.mobileprogramming.project.presentation.model.MinecraftItem;
import com.mobileprogramming.project.presentation.view.MainActivity;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private MainActivity view;
    private SharedPreferences sharedpreferences;
    private Gson gson;

    public MainController(MainActivity view, Gson gson, SharedPreferences sharedPreferences){
        this.view = view;
        this.gson = gson;
        this.sharedpreferences = sharedPreferences;
        List<MinecraftItem> list = getDataFromCache();
        if(list != null) {
            view.showList(list);
        } else {
            makeApiCall();
        }
    }

    private void makeApiCall(){

        Call<List<MinecraftItem>> call = Singletons.getMinecraftInterface().getMinecraftItem();
        call.enqueue(new Callback<List<MinecraftItem>>() {
            @Override
            public void onResponse(Call<List<MinecraftItem>> call, Response<List<MinecraftItem>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<MinecraftItem> itemslist = response.body();
                    view.showList(itemslist);
                    saveList(itemslist);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<List<MinecraftItem>> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<MinecraftItem> itemslist) {
        String jsonString = gson.toJson(itemslist);
        sharedpreferences.edit().putString(Constants.getKEY_MINECRAFT_LIST(), jsonString).apply();
    }

    private List<MinecraftItem> getDataFromCache() {
        String stringminecraft = sharedpreferences.getString(Constants.getKEY_MINECRAFT_LIST(), null);
        if(stringminecraft == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<MinecraftItem>>(){}.getType();
            return gson.fromJson(stringminecraft, listType);
        }
    }
}
