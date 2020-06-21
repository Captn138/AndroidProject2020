package com.mobileprogramming.project.data;

import com.mobileprogramming.project.presentation.model.MinecraftItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MinecraftInterface {
    @GET("items.json")
    Call<List<MinecraftItem>> getMinecraftItem();
}
