package com.epicodus.librarius.adapters;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.librarius.R;
import com.epicodus.librarius.fragments.BarcodeScannerFragment;
import com.epicodus.librarius.fragments.BibliographyFragment;
import com.epicodus.librarius.models.Book;
import com.epicodus.librarius.ui.MainActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/1/16.
 */
public class SearchDisplayListAdapter extends RecyclerView.Adapter<SearchDisplayViewHolder> {
    private ArrayList<Book> mBooks = new ArrayList<>();
    private Context mContext;

    public SearchDisplayListAdapter(Context context, ArrayList<Book> books) {
        mContext = context;
        mBooks = books;
    }

    @Override
    public SearchDisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_display_list_item, parent, false);
        SearchDisplayViewHolder viewHolder = new SearchDisplayViewHolder(view, mBooks);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchDisplayViewHolder holder, int position) {
        holder.bindBook(mBooks.get(position));
    }


    @Override
    public int getItemCount() {
        return mBooks.size();
    }





}
