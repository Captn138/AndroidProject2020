package com.mobileprogramming.td3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    private static final String BASE_URL = "https://minecraft-ids.grahamedgecombe.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
        makeApiCall();
    }

    private void showList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }

        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

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
                    Toast.makeText(getApplicationContext(), "API Success!", Toast.LENGTH_LONG).show();
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

    public void showError() {
        Toast.makeText(this, "API Error!", Toast.LENGTH_LONG).show();
    }
}
