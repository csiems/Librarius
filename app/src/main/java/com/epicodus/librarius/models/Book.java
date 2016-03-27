package com.epicodus.librarius.models;

/**
 * Created by Guest on 3/26/16.
 */
public class Book {
    private String mAuthorFirstName;
    private String mAuthorLastName;
    private String mBookTitle;
    private String mBookVol;
    private String mBookEd;
    private String mBookSeries;
    private String mPublisher;
    private String mBookCity;
    private String mBookYear;

    public Book(String authorFirstName, String authorLastName, String bookTitle, String bookVol, String bookEd, String bookSeries, String publisher, String bookCity, String bookYear) {
        this.mAuthorFirstName = authorFirstName;
        this.mAuthorLastName = authorLastName;
        this.mBookTitle = bookTitle;
        this.mBookVol = bookVol;
        this.mBookEd = bookEd;
        this.mBookSeries = bookSeries;
        this.mPublisher = publisher;
        this.mBookCity = bookCity;
        this.mBookYear = bookYear;
    }

    public Book(String authorFirstName, String authorLastName, String bookTitle, String publisher, String bookCity, String bookYear) {
        this.mAuthorFirstName = authorFirstName;
        this.mAuthorLastName = authorLastName;
        this.mBookTitle = bookTitle;
        this.mPublisher = publisher;
        this.mBookCity = bookCity;
        this.mBookYear = bookYear;
    }



    public String getAuthorFirstName() {
        return mAuthorFirstName;
    }

    public String getAuthorLastName() {
        return mAuthorLastName;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public String getBookVol() {
        return mBookVol;
    }

    public String getBookEd() {
        return mBookEd;
    }

    public String getBookSeries() {
        return mBookSeries;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public String getBookCity() {
        return mBookCity;
    }

    public String getBookYear() {
        return mBookYear;
    }
}
