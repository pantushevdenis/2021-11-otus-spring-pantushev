package ru.otus.spring.pantushev.batch.configuration;

public class CommonConsts {
    public static final String IMPORT_BOOKS_JOB_NAME = "importBooksJob";
    public static final String IMPORT_ALL_JOB_NAME = "importAllJob";
    public static final String IMPORT_AUTHORS_JOB_NAME = "importAuthors";
    public static final String IMPORT_JENRES_JOB_NAME = "importJenres";
    public static final String INPUT_FILE_NAME = "input/books.csv";
    public static final int LINES_TO_SKIP = 1;
    public static final String SPLIT_DELIMITER = ";";
    public static final int CHUNK_SIZE_TO_DICTIONNARY = 4;
    public static final int CHUNK_SIZE_TO_OPERATE = 24;
}
