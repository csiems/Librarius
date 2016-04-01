package com.epicodus.librarius.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.librarius.R;
import com.epicodus.librarius.models.Book;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/1/16.
 */
public class SearchDisplayListAdapter extends RecyclerView.Adapter<SearchDisplayListAdapter.SearchDisplayViewHolder> {
    private ArrayList<Book> mBooks = new ArrayList<>();
    private Context mContext;

    public SearchDisplayListAdapter(Context context, ArrayList<Book> books) {
        mContext = context;
        mBooks = books;
    }

    @Override
    public SearchDisplayListAdapter.SearchDisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_display_list_item, parent, false);
        SearchDisplayViewHolder viewHolder = new SearchDisplayViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchDisplayListAdapter.SearchDisplayViewHolder holder, int position) {
        holder.bindBook(mBooks.get(position));
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }



    public class SearchDisplayViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.searchDisplayTitleTextView) TextView mSearchDisplayTitleTextView;
        @Bind(R.id.searchDisplayAuthorTextView) TextView mSearchDisplayAuthorTextView;
        @Bind(R.id.searchDisplayPublisherTextView) TextView mSearchDisplayPublisherTextView;
        private Context mContext;


        public SearchDisplayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindBook(Book book) {
            mSearchDisplayTitleTextView.setText(book.getBookTitle());
            mSearchDisplayAuthorTextView.setText(book.getAuthorData());
            mSearchDisplayPublisherTextView.setText(book.getPublisher());
        }
    }

}