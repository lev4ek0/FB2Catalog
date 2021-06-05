package com.company;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import static com.company.BookMeta.*;
import static com.company.BookMeta.getTitle;

/**
 * Класс нужен для того, чтобы работать с тем, чтобы достать книгу из файла
 */
public class BookFromFile {

    /**
     * Метод возвращает книгу с заполненными автором и названием книги
     *
     * @param name название файла в каталоге
     * @param fb2 поток на чтение файла
     * @return книгу
     */
    public static Book getBook(String name, InputStream fb2) throws Exception {
        byte[] metaInfo;
        if (".fb2".equals(getFileExtension(name))) {
            metaInfo = fb2.readNBytes(1000);
        }
        else if (".fb2.zip".equals(getFileExtension(name))) {
            metaInfo = fb2.readNBytes(1000);
            InputStream bis1 = new ByteArrayInputStream(metaInfo);
            try (ZipInputStream FB2 = new ZipInputStream(bis1)) {
                FB2.getNextEntry();
                metaInfo = FB2.readNBytes(1000);
            }
            catch (Exception ex) {
                throw new Exception("Can't create ZipInputStream of file \"" + name + "\"");
            }
        } else {
            return null;
        }
        String bookText;
        String encoding = "utf-8";
        try {
            encoding = getEncoding(new String(metaInfo));
        } finally {
            bookText = new String(metaInfo, encoding);
        }
        return new Book(getAuthor(bookText), getTitle(bookText));
    }
}
