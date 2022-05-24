package ru.otus.spring.pantushev.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintCollectionServiceImpl implements PrintCollectionService{
    @Override
    public <T> void printCollection(List<T> collection, String collectionName) {
        System.out.println();
        System.out.println(collectionName + " collection:");
        for(T e : collection) {
            System.out.println(e);
        }
        System.out.println("-----------------");
        System.out.println("items count: " + collection.size());
        System.out.println();
    }
}
