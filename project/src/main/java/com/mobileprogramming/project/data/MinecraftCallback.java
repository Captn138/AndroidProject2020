package com.mobileprogramming.project.data;

import com.mobileprogramming.project.presentation.model.MinecraftItem;

import java.util.List;

public interface MinecraftCallback {
    void onSuccess(List<MinecraftItem> response);
    void onFailed();
}
