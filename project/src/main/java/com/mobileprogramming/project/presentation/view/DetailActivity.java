package com.mobileprogramming.project.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobileprogramming.project.R;
import com.mobileprogramming.project.Singletons;
import com.mobileprogramming.project.presentation.model.MinecraftItem;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView nameTV;
    private TextView minecraftNameTV;
    private TextView text_typeTV;
    private ImageView ItemImageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameTV = findViewById(R.id.nameTV);
        minecraftNameTV = findViewById(R.id.minecraftnameTV);
        text_typeTV = findViewById(R.id.text_typeTV);
        ItemImageIV = (ImageView)findViewById(R.id.ItemImageIV);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String minecraftItemJson = intent.getStringExtra("MinecraftItemKey");
        MinecraftItem item = Singletons.getGson().fromJson(minecraftItemJson, MinecraftItem.class);
        showDetails(item);
    }

    private void showDetails(MinecraftItem item) {
        String nameTVString = "Item name: " + item.getName();
        String minecraftNameTVString;
        if(item.getMeta() == 0){
            minecraftNameTVString = "Minecraft item name: " + item.getTextType();
        }else {
            minecraftNameTVString = "Minecraft item name: " + item.getTextType() + ":" + item.getMeta();
        }
        String text_typeTVString = "Under the set of: " + item.getTextType();
        nameTV.setText(nameTVString);
        minecraftNameTV.setText(minecraftNameTVString);
        text_typeTV.setText(text_typeTVString);
        String path = "file:///android_asset/" + item.getType() + "." + item.getMeta() + " (Small).gif";
        Picasso.get().load(path).into(ItemImageIV);
    }
}
