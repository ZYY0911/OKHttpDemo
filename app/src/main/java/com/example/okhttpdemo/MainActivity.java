package com.example.okhttpdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       OkHttpTo okHttpTo = new OkHttpTo();
       okHttpTo.setUrl("get_all_sense")
               .setJsonObject("UserName","user1")
               .setLoop(true)
               .setTime(3000)
               .setOkHttpLo(new OkHttpLo() {
                   @Override
                   public void onResponse(@NotNull Call call, @NotNull JSONObject response) {
                       Log.i("aaa", "onResponse: "+response.toString());
                   }

                   @Override
                   public void onFailure(@NotNull Call call, @NotNull IOException e) {

                   }
               }).start();
    }
}