/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianfennen.java.dataObjects;

/**
 *
 * @author Ian Fennen
 */
public class BookReg {
    private String title;
    private String author;
    private String isbn;
    
    public BookReg(String title, String author, String isbn){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    public BookReg(){
        this.title = "Untitled";
        this.author = "No Author";
        this.isbn = "-1";
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
    
    
    
    public Object[] toArray(){
        Object ar[] = {title, author, isbn};
        return ar;
    }
    
    public boolean equals(Object obj){
        return obj instanceof BookReg && ((BookReg)obj).toString().equals(this.toString());
    }
    
    @Override
    public String toString(){
        return author + "," + title + "," + isbn; 
    }
}
