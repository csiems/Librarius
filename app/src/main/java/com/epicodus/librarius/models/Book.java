package com.epicodus.librarius.models;

/**
 * Created by Guest on 3/26/16.
 */
public class Book {
    private String authorData;
    private String bookTitle;
    private String editionInfo;
    private String publisher;
    String index;


    public Book(String authorData, String bookTitle, String editionInfo, String publisher) {
        this.authorData = authorData;
        this.bookTitle = bookTitle;
        this.editionInfo = editionInfo;
        this.publisher = publisher;
        this.index = "not_specified";
    }

    public Book(String authorData, String bookTitle, String publisher) {
        this.authorData = authorData;
        this.bookTitle = bookTitle;
        this.publisher = publisher;
        this.index = "not_specified";
    }

    public Book() {
    }



    public String getAuthorData() {
        return authorData;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getEditionInfo() {
        return editionInfo;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
