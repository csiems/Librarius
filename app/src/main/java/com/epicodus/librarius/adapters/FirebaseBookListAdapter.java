package com.epicodus.librarius.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.librarius.R;
import com.epicodus.librarius.models.Book;
import com.firebase.client.Query;

/**
 * Created by Guest on 4/5/16.
 */
public class FirebaseBookListAdapter extends FirebaseRecyclerAdapter<SearchDisplayViewHolder, Book> {
    public FirebaseBookListAdapter(Query query, Class<Book> itemClass) {
        super(query, itemClass);
    }

    @Override
    public SearchDisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_display_list_item, parent, false);
        return new SearchDisplayViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(SearchDisplayViewHolder holder, int position) {
        holder.bindBook(getItem(position));
    }

    @Override
    protected void itemAdded(Book item, String key, int position) {

    }

    @Override
    protected void itemChanged(Book oldItem, Book newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Book item, String key, int position) {

    }

    @Override
    protected void itemMoved(Book item, String key, int oldPosition, int newPosition) {

    }
}
