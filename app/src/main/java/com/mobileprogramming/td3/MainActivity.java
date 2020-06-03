package com.mobileprogramming.td3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String BASE_URL = Constants.getAPI_URL();
    private SharedPreferences sharedpreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(Constants.getNAME_SHARED_PREFS(), Context.MODE_PRIVATE);
        gson = new GsonBuilder().setLenient().create();
        List<MinecraftItem> list = getDataFromCache();
        if(list != null) {
            showList(list);
        } else {
            makeApiCall();
        }
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

    private void showList(List<MinecraftItem> list) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(list);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MinecraftInterface mcapi = retrofit.create(MinecraftInterface.class);

        Call<List<MinecraftItem>> call = mcapi.getMinecraftItem();
        call.enqueue(new Callback<List<MinecraftItem>>() {
            @Override
            public void onResponse(Call<List<MinecraftItem>> call, Response<List<MinecraftItem>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<MinecraftItem> itemslist = response.body();
                    showList(itemslist);
                    saveList(itemslist);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<MinecraftItem>> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<MinecraftItem> itemslist) {
        String jsonString = gson.toJson(itemslist);
        sharedpreferences.edit().putString(Constants.getKEY_MINECRAFT_LIST(), jsonString).apply();
    }

    private void showError() {
        Toast.makeText(this, Constants.getERROR(), Toast.LENGTH_LONG).show();
    }
}
