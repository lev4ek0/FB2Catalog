package com.company;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.company.BookFromFile.getBook;

/**
 * Класс позволяет работать с zip архивами, в которых находятся книги
 */
public class BooksFromZip {

    /**
     * Метод считывает файлы из архива. Обрабатываются файлы с расширением .fb2 и .fb2.zip
     * Из метаинформации этих файлов достаются нужные данные и на выходе мы получаем список книг,
     * с которым легко работать
     *
     * @param path путь на директорию, из которой нужно достать книги
     * @return список книг в директории
     */
    public static List<Book> getBooksFromZip(String path) throws Exception {
        ArrayList<Book> books = new ArrayList<>();

        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry;
            String name;

            while ((entry = zip.getNextEntry()) != null) {
                name = entry.getName();

                Book book = getBook(name, zip);
                if (!books.contains(book) && book != null) {
                    books.add(book);
                }
                zip.closeEntry();
            }
        }
        return books;
    }
}
