package com.denztri.postdudeclient.utils;

import android.util.Log;

import com.denztri.postdudeclient.ui.query.QueryModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestBuilder {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    final OkHttpClient client = new OkHttpClient();

    public String headers;
    public String cookie;
    public int code;
    public String codeStaus;
    public long contentLength;



    public String run(String url, List<QueryModel> query) throws IOException {
        HttpUrl.Builder httpBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (!query.get(0).getKey().isEmpty()) {
            query.forEach(queryModel -> httpBuilder.addQueryParameter(queryModel.getKey(),queryModel.getValue()));
        }

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();
        Log.d("HTTPMEMEK", httpBuilder.build().toString());
        try (Response response = client.newCall(request).execute()) {
            setHeaders(response.headers().toString());
            setCookie(response.headers("Set-Cookie").toString());
            setCode(response.code());
            setContentLength(response.body().contentLength());
            Log.d("CONTENT LENG",String.valueOf(response.body().contentLength()));
            return response.body().string();
        }
    }

    public String run(String url,List<QueryModel> query, String jsonBody) throws IOException{
        RequestBody body = RequestBody.create(jsonBody, JSON);
        HttpUrl.Builder httpBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (!query.get(0).getKey().isEmpty()) {
            query.forEach(queryModel -> httpBuilder.addQueryParameter(queryModel.getKey(),queryModel.getValue()));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            setHeaders(response.headers().toString());
            setCookie(response.headers("Set-Cookie").toString());
            setCode(response.code());
            setContentLength(response.body().contentLength());
            return response.body().string();
        }
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeStaus() {
        return codeStaus;
    }

    public void setCodeStaus(String codeStaus) {
        this.codeStaus = codeStaus;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
}
