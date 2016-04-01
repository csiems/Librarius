package com.epicodus.librarius.services;

import android.content.Context;
import android.util.Log;

import com.epicodus.librarius.R;
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
public class ISBNDBService {
    private Context mContext;

    public ISBNDBService(Context context) {
        this.mContext = context;
    }

    public void findBooks(String searchTerm, Callback callback) {
        String API_KEY = mContext.getString(R.string.API_KEY);

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://isbndb.com/api/v2/json/" + API_KEY + "/books").newBuilder();
        urlBuilder.addQueryParameter("q", searchTerm);
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
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject responseJSON = new JSONObject(jsonData);
                JSONArray dataJSON = responseJSON.getJSONArray("data");
                for (int i = 0; i < dataJSON.length(); i++) {
                    JSONObject bookJSON = dataJSON.getJSONObject(i);

                    ArrayList<String> authorArray = new ArrayList<>();
                    JSONArray authorNamesJSON = bookJSON.getJSONArray("author_data");
                    for (int y = 0; y < authorNamesJSON.length(); y++) {
                        String author = authorNamesJSON.getJSONObject(y).getString("name");
                        authorArray.add(author);
                    }
                    String authorData = "";
                    for (String author : authorArray) {
                        authorData += author + "\t";
                    }

                    String bookTitle = bookJSON.getString("title");
                    String editionInfo = bookJSON.getString("edition_info");
                    String publisherText = bookJSON.getString("publisher_text");

                    Book book = new Book(authorData, bookTitle, editionInfo, publisherText);
                    books.add(book);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }


}
