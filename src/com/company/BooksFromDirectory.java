package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.company.BookFromFile.getBook;

/**
 * Класс позволяет работать с директориями, в которых находятся книги
 */
public class BooksFromDirectory {

    /**
     * Метод считывает файлы из директории. Обрабатываются файлы с расширением .fb2 и .fb2.zip
     * Из метаинформации этих файлов достаются нужные данные и на выходе мы получаем список книг,
     * с которым легко работать
     *
     * @param file директория, из которой нужно достать книги
     * @return список книг в директории
     */
    public static List<Book> getBooksFromDirectory(File file) throws Exception {
        ArrayList<Book> books = new ArrayList<>();

        for(File item : Objects.requireNonNull(file.listFiles())){

            if(item.isFile()){
                FileInputStream fb2 = new FileInputStream(item);
                String name = item.getName();
                Book book = getBook(name, fb2);
                if (!books.contains(book) && book != null) {
                    books.add(book);
                }
                fb2.close();
            }
        }
        return books;
    }
}
