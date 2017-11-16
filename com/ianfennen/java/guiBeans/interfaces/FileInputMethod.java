/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianfennen.java.guiBeans.interfaces;

import com.ianfennen.java.dataObjects.Book;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ian Fennen
 */
public abstract class FileInputMethod extends javax.swing.JPanel {
    
    /**
     * Returns the {@code bookList}, can be {@code null} if the book has not been loaded.
     *
     * @return The {@code bookList} with the loaded books. Returns {@code null} if file has not been loaded.
     */
    public ArrayList<Book> getBookList() {
        return bookList;
    }
    
    /**
     * {@code List} That contains all books loaded, null if file not loaded.
     */
    protected ArrayList<Book> bookList = null;
    
    
    /**
     * Displays an error window with text {@code errorMsg}.
     *
     * @param errorMsg The error message displayed to the user. This is the entire text shown.
     */
    protected void displayError(String errorMsg) {
        JOptionPane.showMessageDialog(this,
                errorMsg,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
