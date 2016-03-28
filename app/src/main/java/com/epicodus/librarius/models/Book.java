package com.epicodus.librarius.models;

import java.util.ArrayList;

/**
 * Created by Guest on 3/26/16.
 */
public class Book {
    private ArrayList<String> mAuthorData;
    private String mBookTitle;
    private String mEditionInfo;
    private String mPublisherText;

    public Book(ArrayList<String> authorData, String bookTitle, String editionInfo, String publisherText) {
        this.mAuthorData = authorData;
        this.mBookTitle = bookTitle;
        this.mEditionInfo = editionInfo;
        this.mPublisherText = publisherText;
    }

    public Book(ArrayList<String> authorData, String bookTitle, String publisherText) {
        this.mAuthorData = authorData;
        this.mBookTitle = bookTitle;
        this.mPublisherText = publisherText;
    }



    public ArrayList<String> getAuthorData() {
        return mAuthorData;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public String getEditionInfo() {
        return mEditionInfo;
    }

    public String getPublisher() {
        return mPublisherText;
    }

}
