package com.epicodus.librarius.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.librarius.R;
import com.epicodus.librarius.models.Book;
import com.epicodus.librarius.util.OnSearchedBookSelectedListener;

import java.util.ArrayList;

/**
 * Created by Guest on 4/1/16.
 */
public class BookListAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private ArrayList<Book> mBooks = new ArrayList<>();
    public BookViewHolder viewHolder;
    private Context mContext;
    private OnSearchedBookSelectedListener mOnSearchedBookSelectedListener;

    public BookListAdapter(Context context, ArrayList<Book> books) {
        mBooks = books;
        mContext = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_display_list_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(view, mBooks);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bindBook(mBooks.get(position));
    }


    @Override
    public int getItemCount() {
        return mBooks.size();
    }





}
