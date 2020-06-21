package com.mobileprogramming.project.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mobileprogramming.project.R;
import com.mobileprogramming.project.data.MinecraftInterface;
import com.mobileprogramming.project.presentation.controller.MainController;
import com.mobileprogramming.project.presentation.model.Constants;
import com.mobileprogramming.project.presentation.model.MinecraftItem;

import java.lang.reflect.Type;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainController controller = new MainController(this, new GsonBuilder().setLenient().create(), getSharedPreferences(Constants.getNAME_SHARED_PREFS(), Context.MODE_PRIVATE));
    }

    public void showList(List<MinecraftItem> list) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(list);
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(this, Constants.getERROR(), Toast.LENGTH_LONG).show();
    }
}
