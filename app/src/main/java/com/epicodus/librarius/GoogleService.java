package com.epicodus.librarius;

import android.content.Context;

import com.epicodus.librarius.models.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public ArrayList<Book> processResults(Response response) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String jsonData = response.body().toString();
            if (response.isSuccessful()) {
                JSONObject googleJSON = new JSONObject(jsonData);
                JSONArray itemsJSON = googleJSON.getJSONArray("items");
                for (int i = 0; i < itemsJSON.length(); i++) {
                    JSONObject itemJSON = itemsJSON.getJSONObject(i);
                    String kind = itemJSON.getString("kind");
                    String id = itemJSON.getString("id");
                    String etag = itemJSON.getString("etag");
                    String selfLink = itemJSON.getString("selfLink");
                    String title = itemJSON.getJSONObject("volumeInfo").getString("title");

                    ArrayList<String> authors = new ArrayList<>();
                    JSONArray authorsJSON = itemJSON.getJSONObject("volumeInfo").getJSONArray("authors");
                    for (int y = 0; y < authorsJSON.length(); y++) {
                        authors.add(authorsJSON.get(y).toString());
                    }
//
//                    Book book = new Book();
//                    books.add(book);



                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }


}
