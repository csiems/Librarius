package com.epicodus.librarius.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.librarius.LibrariusApplication;
import com.epicodus.librarius.R;
import com.epicodus.librarius.fragments.BibliographyFragment;
import com.epicodus.librarius.models.Book;
import com.epicodus.librarius.ui.MainActivity;
import com.firebase.client.Firebase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BookViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.bibliographyTitleTextView)
    TextView mSearchDisplayTitleTextView;
    @Bind(R.id.bibliographyAuthorTextView) TextView mSearchDisplayAuthorTextView;
    @Bind(R.id.searchDisplayPublisherTextView) TextView mSearchDisplayPublisherTextView;
    private Context mContext;
    private ArrayList<Book> mBooks = new ArrayList<>();
    private Book mBook;
    private Firebase mFirebaseRef;
    private String mCurrentUserUid;


    public BookViewHolder(View itemView, ArrayList<Book> books) {
        super(itemView);
        mContext = itemView.getContext();
        mBooks = books;
        mFirebaseRef = LibrariusApplication.getAppInstance().getFirebaseRef();
        mCurrentUserUid = mFirebaseRef.getAuth().getUid();

        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                mBook = mBooks.get(itemPosition);
                mFirebaseRef.child(mCurrentUserUid + "/" + mBook.getBookTitle()).setValue(mBook);
                Toast.makeText(mContext, R.string.savedToastMessage, Toast.LENGTH_SHORT).show();
                BibliographyFragment bibliographyFragment = BibliographyFragment.newInstance();
                ((MainActivity) mContext).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content_layout, bibliographyFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void bindBook(Book book) {
        mSearchDisplayTitleTextView.setText(book.getBookTitle());
        mSearchDisplayAuthorTextView.setText(book.getAuthorData());
        mSearchDisplayPublisherTextView.setText(book.getPublisher());
    }
}

