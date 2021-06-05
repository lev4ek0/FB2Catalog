package com.company;

/**
 * Класс содержит методы для извлечения метаинформации
 */
public class BookMeta {

    /**
     * Метод возвращает название книги. В метаинформации название находится между тэгами <book-title> и </book-title>
     *
     * @param bookText первые 1000 байт книги, в которых будет вся метаинформация к ней
     * @return название книги
     */
    public static String getTitle(String bookText) throws Exception {
        try {
            bookText = bookText.substring(bookText.indexOf("<book-title>"), bookText.indexOf("</book-title>"));
        }
        catch (Exception ex) {
            throw new Exception("No title");
        }
        return bookText.substring(12);
    }

    /**
     * Метод находит по ключевому слову кодировку файла. Если ее нет, то метод пробрасывает исключение
     *
     * @param BookText первые 1000 байт книги, в которых будет вся метаинформация к ней
     * @return кодировка книги
     */
    public static String getEncoding(String BookText) throws Exception {
        int firstIndex = BookText.indexOf("encoding=\"");
        if (firstIndex == -1){
            throw new Exception("No encoding");
        }
        BookText = BookText.substring(firstIndex);
        int lastIndex = BookText.indexOf("\"?");
        return BookText.substring(10, lastIndex);
    }

    /**
     * Метод возвращает автора книги. В метаинформации автор находится между тэгами <author> и </author>
     * Внутри могут находиться тэги <first-name>,<last-name>,<middle-name>, которые соединяются в автора.
     *
     *
     * @param bookText первые 1000 байт книги, в которых будет вся метаинформация к ней
     * @return автор книги
     */
    public static String getAuthor(String bookText) throws Exception {
        String nameSearch = "name>";
        StringBuilder nameOfAuthor = new StringBuilder();
        try {
            bookText = bookText.substring(bookText.indexOf("<author>"), bookText.indexOf("</author>"));
        }
        catch (Exception ex){
            throw new Exception("No author");
        }
        while (bookText.contains(nameSearch)){
            int index = bookText.indexOf(nameSearch) + 5;
            bookText = bookText.substring(index);
            nameOfAuthor.append(bookText, 0, bookText.indexOf('<')).append(" ");
            index = bookText.indexOf(nameSearch) + 5;
            bookText = bookText.substring(index);
        }
        return nameOfAuthor.substring(0, nameOfAuthor.length() - 1);
    }

    /**
     * Метод возвращает расширение файла. Если файл без расширения, то возвращается null
     *
     * @param name название книги в каталоге
     * @return расширение книги
     */
    public static String getFileExtension(String name) {
        int index = name.indexOf('.');
        if (index == -1) {
            return null;
        } else {
            return name.substring(index);
        }
    }
}
