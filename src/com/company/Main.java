package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static com.company.BooksFromDirectory.getBooksFromDirectory;
import static com.company.BooksFromZip.getBooksFromZip;

/**
 * Это класс с меню программы. На вход в консоль подается команда. В зависимости от команды
 * выполняются действия, описанные в условии меню. На вход может подаваться как папка, так и зип архив.
 */
public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("To check list of books write \"Check\". To end the program type \"End\". For help write \"Help\"");
        String choice = reader.next();

        while(!"End".equals(choice)) {
            switch (choice) {
                case "Check" -> {
                    System.out.print("Введите путь: ");
                    String path = reader.next();
                    //C:\Users\Lev\Downloads\books.zip;
                    File file = new File(path);
                    List<Book> books = new ArrayList<>();
                    if (file.isDirectory()){
                        try {
                            books = getBooksFromDirectory(file);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        try {
                            books = getBooksFromZip(path);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    books.sort(Comparator.comparing(Book::getInfo));
                    for (Book book: books) {
                        System.out.println(book.getInfo());
                    }
                    if (books.isEmpty()) {
                        System.out.println("No books in this directory");
                    }
                    choice = "Help";
                }
                case "Help" -> {
                    System.out.println("\nTo check list of books write \"Check\". To end the program type \"End\". For help write \"Help\"");
                    choice = reader.next();
                }
                default -> {
                    System.out.print("Wrong command");
                    choice = "Help";
                }
            }
        }
    }
}
