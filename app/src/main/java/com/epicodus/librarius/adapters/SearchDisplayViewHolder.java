package com.epicodus.librarius.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.librarius.R;
import com.epicodus.librarius.models.Book;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/5/16.
*/
public class SearchDisplayViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.searchDisplayTitleTextView)
    TextView mSearchDisplayTitleTextView;
    @Bind(R.id.searchDisplayAuthorTextView) TextView mSearchDisplayAuthorTextView;
    @Bind(R.id.searchDisplayPublisherTextView) TextView mSearchDisplayPublisherTextView;
    private Context mContext;

    public SearchDisplayViewHolder(View itemView, ArrayList<Book> books) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //int itemPosition = getLayoutPosition();
                //TODO: save to firebase
                Toast.makeText(mContext, "You did it!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void bindBook(Book book) {
        mSearchDisplayTitleTextView.setText(book.getBookTitle());
        mSearchDisplayAuthorTextView.setText(book.getAuthorData());
        mSearchDisplayPublisherTextView.setText(book.getPublisher());
    }
}

