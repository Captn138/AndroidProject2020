package com.mobileprogramming.project.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobileprogramming.project.R;
import com.mobileprogramming.project.presentation.Singletons;
import com.mobileprogramming.project.presentation.model.MinecraftItem;

public class DetailActivity extends AppCompatActivity {

    private TextView nameTV;
    private TextView minecraftnameTV;
    private TextView text_typeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameTV = findViewById(R.id.nameTV);
        minecraftnameTV = findViewById(R.id.minecraftnameTV);
        text_typeTV = findViewById(R.id.text_typeTV);
        Intent intent = getIntent();
        String minecraftItemJson = intent.getStringExtra("MinecraftItemKey");
        MinecraftItem item = Singletons.getGson().fromJson(minecraftItemJson, MinecraftItem.class);
        showDetails(item);
    }

    private void showDetails(MinecraftItem item) {
        String nameTVstring = "Item name: " + item.getName();
        String minecraftnameTVstring = "Minecraft item name: " + item.getTextType() + ":" + item.getMeta();
        String text_typeTVstring = "Under the set of: " + item.getTextType();
        nameTV.setText(nameTVstring);
        minecraftnameTV.setText(minecraftnameTVstring);
        text_typeTV.setText(text_typeTVstring);
    }
}
