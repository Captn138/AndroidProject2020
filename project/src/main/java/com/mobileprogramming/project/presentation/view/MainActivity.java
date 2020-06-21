package com.mobileprogramming.project.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.mobileprogramming.project.R;
import com.mobileprogramming.project.presentation.Singletons;
import com.mobileprogramming.project.presentation.controller.MainController;
import com.mobileprogramming.project.presentation.Constants;
import com.mobileprogramming.project.presentation.model.MinecraftItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainController controller = new MainController(this, Singletons.getGson(), Singletons.getSharedPreferences(getApplicationContext()));
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
