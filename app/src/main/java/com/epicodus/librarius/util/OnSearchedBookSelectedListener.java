package com.epicodus.librarius.util;

import com.epicodus.librarius.models.Book;

import java.util.ArrayList;

/**
 * Created by lsiems on 4/6/16.
 */
public interface OnSearchedBookSelectedListener {
  public void onSearchedBookSelected(Integer position, ArrayList<Book> books);
}
