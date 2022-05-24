package ru.otus.spring.pantushev.services;

import java.util.List;

public interface PrintCollectionService {
        <T> void printCollection(List<T> collection, String collectionName);
}
