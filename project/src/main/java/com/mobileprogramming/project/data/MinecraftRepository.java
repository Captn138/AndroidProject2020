package com.mobileprogramming.project.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobileprogramming.project.Constants;
import com.mobileprogramming.project.presentation.model.MinecraftItem;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinecraftRepository {
    private MinecraftApi minecraftApi;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public MinecraftRepository(MinecraftApi minecraftApi, SharedPreferences sharedPreferences, Gson gson) {
        this.minecraftApi = minecraftApi;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void getMinecraftItem(final MinecraftCallback callback) {
        List<MinecraftItem> list = getDataFromCache();
        if(list != null) {
            callback.onSuccess(list);
        } else {
            minecraftApi.getMinecraftItem().enqueue(new Callback<List<MinecraftItem>>() {
                @Override
                public void onResponse(Call<List<MinecraftItem>> call, Response<List<MinecraftItem>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<List<MinecraftItem>> call, Throwable t) {
                    callback.onFailed();
                }
            });
        }
    }

    private List<MinecraftItem> getDataFromCache() {
        String stringMinecraft = sharedPreferences.getString(Constants.getKEY_MINECRAFT_LIST(), null);
        if(stringMinecraft == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<MinecraftItem>>(){}.getType();
            return gson.fromJson(stringMinecraft, listType);
        }
    }

    private void saveList(List<MinecraftItem> itemslist) {
        String jsonString = gson.toJson(itemslist);
        sharedPreferences.edit().putString(Constants.getKEY_MINECRAFT_LIST(), jsonString).apply();
    }

}
