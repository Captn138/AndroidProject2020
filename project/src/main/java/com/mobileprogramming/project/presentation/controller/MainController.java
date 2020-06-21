package com.mobileprogramming.project.presentation.controller;

import com.mobileprogramming.project.data.MinecraftCallback;
import com.mobileprogramming.project.data.MinecraftRepository;
import com.mobileprogramming.project.presentation.model.MinecraftItem;
import com.mobileprogramming.project.presentation.view.MainActivity;

import java.util.List;

public class MainController {

    private MainActivity view;
    private MinecraftRepository repository;

    public MainController(MainActivity view, MinecraftRepository repository){
        this.view = view;
        this.repository = repository;
    }

    public void onStart(){
        repository.getMinecraftItem(new MinecraftCallback() {
            @Override
            public void onSuccess(List<MinecraftItem> response) {
                view.showList(response);
            }

            @Override
            public void onFailed() {
                view.showError();
            }
        });
    }

    public void onItemClick(MinecraftItem item) {
        view.navigateToDetails(item);
    }
}
