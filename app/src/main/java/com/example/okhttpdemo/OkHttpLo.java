package com.example.okhttpdemo;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/15 at 17:37
 */
public interface OkHttpLo   {
    void onResponse(@NotNull Call call, @NotNull JSONObject response);
    void onFailure(@NotNull Call call, @NotNull IOException e);
}
