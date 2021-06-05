package com.company;

import java.util.Objects;

/**
 * Класс хранит информацию о книге, которая нам нужна, возвращает название, автор, автор-название.
 */
public class Book {

    /**
     * author автор книги
     * title название книги
     */
    private final String author;
    private final String title;

    Book(String author, String title){
        this.author = author;
        this.title = title;
    }

    public String getInfo(){
        return author + " - " + title;
    }

    public String getAuthor(){
        return author;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            Book b = (Book) obj;
            return ((this == obj) || (getAuthor().equals(b.getAuthor()) && getTitle().equals(b.getTitle())));
        }
        return false;
    }
}
