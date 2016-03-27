package com.epicodus.librarius;

import android.content.Context;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Guest on 3/26/16.
 */
public class GoogleService {
    private Context mContext;

    public GoogleService(Context context) {
        this.mContext = context;
    }

    public void searchBooks(String searchTerm, Callback callback) {
        String API_KEY = mContext.getString(R.string.API_KEY);

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.googleapis.com/books/v1/volumes?q=").newBuilder();
        urlBuilder.addQueryParameter("searchTerm", searchTerm);
        urlBuilder.addQueryParameter("key", API_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
