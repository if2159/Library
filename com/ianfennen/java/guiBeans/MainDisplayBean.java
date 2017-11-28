/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianfennen.java.guiBeans;

import com.ianfennen.java.dataObjects.BookReg;
import com.ianfennen.java.xml.Book;
import com.ianfennen.java.xml.Library;
import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Ian Fennen
 */
public class MainDisplayBean extends javax.swing.JPanel {

    private ArrayList<BookReg> bookList;
    private int inputType;

    /**
     * Creates new form MainDisplayBean
     */
    @SuppressWarnings("Convert2Lambda")
    public MainDisplayBean() {
        initComponents();
        bookList = new ArrayList();
        genericLoaderBean1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(TextFileLoaderBean.FILE_LOADER_EVENT)) {
                    if (evt.getNewValue().equals(TextFileLoaderBean.FILE_STATE_CHANGED)) {
                        bookList = genericLoaderBean1.getBookList();
                        insertDataIntoTable(bookList);
                    }
                }
            }

        });
        
        updateFileBean1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("Property change event " + evt.getPropertyName());
                if (evt.getPropertyName().equals(UpdateFileBean.UPDATE_FILE_EVENT)) {
                    //if (evt.getNewValue().equals(UpdateFileBean.SAVE_FILE_EVENT)) {
                    System.out.println("Save Table");
                    saveTable(dataTableBean1.getBookList(), (String) evt.getNewValue());
                    //}
                }
                System.out.println(evt.getPropertyName());
            }
        });

        inputType = 0;
    }

    public void saveTable(ArrayList<BookReg> bookList, String fileName) {
        System.out.println("Output file");
        switch(inputType){
            case 0:
                saveCSV(bookList, fileName);
                break;
            case 1:
                System.out.println("XML 1");
                saveXML(bookList, fileName);
                break;
            case 2:
                break;
                
        }
        

    }
    
    private void saveXML(ArrayList<BookReg> bookList, String fileName){
        System.out.println("XML");
        Library l = new Library();
        for(BookReg bb : bookList){
            Book b = new Book();
            b.setAuthor(bb.getAuthor());
            b.setISBN(bb.getIsbn());
            b.setTitle(bb.getTitle());
            l.getBook().add(b);
        }
        
        try {
            if(!fileName.endsWith(".xml")){
                fileName = fileName + ".xml";
            }
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //jaxbMarshaller.marshal(b, file);
            jaxbMarshaller.marshal(l, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private void saveCSV(ArrayList<BookReg> bookList, String fileName){
        try (PrintWriter pw = new PrintWriter(new File(fileName))) {
            for (BookReg b : bookList) {
                pw.write(b.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertDataIntoTable(ArrayList<BookReg> bookList) {
        dataTableBean1.updateTable(bookList);
    }

    public void setInputMethod(int type) {
        if (inputType != type) {
            inputType = type;
            changeInputDisplay();
        }

    }

    private void changeInputDisplay() {
        switch (inputType) {
            case 1:
                genericLoaderBean1.changeCard("XMLFileCard");
                break;
            case 2:
                genericLoaderBean1.changeCard("SQLDataCard");
                break;
            case 0:
            default:
                genericLoaderBean1.changeCard("TextFileCard");
                break;

        }
    }
    
    public void changeCard(String cardName) {
        CardLayout cardLayout = (CardLayout) this.getLayout();
        cardLayout.show(this, cardName);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataTableBean1 = new com.ianfennen.java.guiBeans.DataTableBean();
        updateFileBean1 = new com.ianfennen.java.guiBeans.UpdateFileBean();
        genericLoaderBean1 = new com.ianfennen.java.guiBeans.GenericLoaderBean();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataTableBean1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateFileBean1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(genericLoaderBean1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(genericLoaderBean1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(dataTableBean1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateFileBean1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ianfennen.java.guiBeans.DataTableBean dataTableBean1;
    private com.ianfennen.java.guiBeans.GenericLoaderBean genericLoaderBean1;
    private com.ianfennen.java.guiBeans.UpdateFileBean updateFileBean1;
    // End of variables declaration//GEN-END:variables
}
