package com.example.okhttpdemo;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/15 at 17:30
 */
public class OkHttpTo extends Thread{
    private String Url = "http://118.190.26.201:8080/traffic/";
    private OkHttpLo okHttpLo;
    private boolean isLoop;
    private int time;
    private JSONObject  jsonObject = new JSONObject();

    public OkHttpTo setUrl(String url) {
        Url += url;
        return this;

    }

    public OkHttpTo setOkHttpLo(OkHttpLo okHttpLo) {
        this.okHttpLo = okHttpLo;
        return this;
    }

    public OkHttpTo setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public OkHttpTo setTime(int time) {
        this.time = time;
        return this;
    }

    public OkHttpTo setJsonObject(String k ,Object v) {
        try {
            jsonObject.put(k,v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            OkHttpClient client =  new OkHttpClient();
            RequestBody requestBody = RequestBody.create(jsonObject.toString(), MediaType.get("application/json;charset=utf-8"));
            Request request = new Request.Builder()
                    .url(Url)
                    .post(requestBody)
                    .build();
            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            okHttpLo.onFailure(call,e);
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            try {
                                okHttpLo.onResponse(call,new JSONObject(response.body().string()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isLoop);

    }
}
