package ru.otus.spring.pantushev.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Домен Book")
public class BookTest {
    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 1")
    void testEqualsShouldReturnTrueForEqualsObjects1() {
        Author a1 = new Author(1L, "Author1");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Comment c1 = new Comment(1L, "comment");
        Jenre j2 = new Jenre(1L, "Jenre1");
        Comment c2 = new Comment(1L, "comment");
        Jenre j2_diff = new Jenre(2L, "Jenre1");
        Comment c2_diff = new Comment(2L, "comment");

        Book b1 = Book.builder("Book", a1, j1).id(1L).publishingYear(2001).build();
        Book b2 = Book.builder("Book", a1, j1).id(1L).publishingYear(2001).comments(Arrays.asList(c1)).build();
        assertTrue(b1.equals(b2));
        assertEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 2")
    void testEqualsShouldReturnTrueForEqualsObjects2() {
        Author a1 = new Author(1L, "Author1");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Comment c1 = new Comment(1L, "comment");
        Jenre j2 = new Jenre(1L, "Jenre1");
        Comment c2 = new Comment(1L, "comment");
        Jenre j2_diff = new Jenre(2L, "Jenre1");
        Comment c2_diff = new Comment(2L, "comment");

        Book b1 = Book.builder("Book", a1, j1).id(1L).publishingYear(2001).build();
        Book b2 = Book.builder( "Book", a1, j1).id(2L).publishingYear(2001).build();
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 3")
    void testEqualsShouldReturnTrueForEqualsObjects3() {
        Author a1 = new Author(1L, "Author1");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Comment c1 = new Comment(1L, "comment");
        Jenre j2 = new Jenre(1L, "Jenre1");
        Comment c2 = new Comment(1L, "comment");
        Jenre j2_diff = new Jenre(2L, "Jenre1");
        Comment c2_diff = new Comment(2L, "comment");

        Book b1 = Book.builder( "Book", a1, j1).id(1L).publishingYear(2001).build();
        Book b2 = Book.builder( "Book2", a1, j1).id(1L).publishingYear(2001).build();
        assertFalse(b1.equals(32_2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 4")
    void testEqualsShouldReturnTrueForEqualsObjects4() {
        Author a1 = new Author(1L, "Author1");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Comment c1 = new Comment(1L, "comment");
        Author a2 = new Author(1L, "Author1");
        Jenre j2 = new Jenre(1L, "Jenre1");
        Comment c2 = new Comment(1L, "comment");
        Author a1_diff = new Author(1L, "Author2");
        Jenre j2_diff = new Jenre(2L, "Jenre1");

        Book b1 = Book.builder("Book", a1, j1).id(1L).publishingYear(2001).build();
        Book b2 = Book.builder("Book", a2, j1).id(1L).publishingYear(2001).build();
        assertTrue(b1.equals(b2));
        assertEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 5")
    void testEqualsShouldReturnTrueForEqualsObjects5() {
        Author a1 = new Author(1L, "Author1");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Comment c1 = new Comment(1L, "comment");
        Author a2 = new Author(1L, "Author1");
        Jenre j2 = new Jenre(1L, "Jenre1");
        Comment c2 = new Comment(1L, "comment");
        Author a2_diff = new Author(1L, "Author2");
        Jenre j2_diff = new Jenre(2L, "Jenre1");

        Book b1 = Book.builder("Book", a1, j1).id(1L).publishingYear(2001).build();
        Book b2 = Book.builder("Book", a2_diff, j1).id(1L).publishingYear(2001).build();
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 6")
    void testEqualsShouldReturnTrueForEqualsObjects6() {
        Author a1 = new Author(1L, "Author1");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Comment c1 = new Comment(1L, "comment");
        Author a2 = new Author(1L, "Author1");
        Jenre j2 = new Jenre(1L, "Jenre1");
        Comment c2 = new Comment(1L, "comment");
        Author a1_diff = new Author(1L, "Author2");
        Jenre j2_diff = new Jenre(2L, "Jenre1");

        Book b1 = Book.builder("Book", a1, j1).id(1L).publishingYear(2001).build();
        Book b2 = Book.builder("Book", a1, j2).id(1L).publishingYear(2001).build();
        assertTrue(b1.equals(b2));
        assertEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 7")
    void testEqualsShouldReturnTrueForEqualsObjects7() {
        Author a1 = new Author(1L, "Author1");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Comment c1 = new Comment(1L, "comment");
        Author a2 = new Author(1L, "Author1");
        Jenre j2 = new Jenre(1L, "Jenre1");
        Comment c2 = new Comment(1L, "comment");
        Author a2_diff = new Author(1L, "Author2");
        Jenre j2_diff = new Jenre(2L, "Jenre1");

        Book b1 = Book.builder("Book", a1, j1).id(1L).publishingYear(2001).build();
        Book b2 = Book.builder("Book", a1, j2_diff).id(1L).publishingYear(2001).build();
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 8")
    void testEqualsShouldReturnTrueForEqualsObjects8() {
        Author a1 = new Author(1L, "Author1");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Comment c1 = new Comment(1L, "comment");
        Author a2 = new Author(1L, "Author1");
        Jenre j2 = new Jenre(1L, "Jenre1");
        Comment c2 = new Comment(1L, "comment");
        Author a2_diff = new Author(1L, "Author2");
        Jenre j2_diff = new Jenre(2L, "Jenre1");

        Book b1 = Book.builder("Book", a1, j1).id(1L).publishingYear(2001).build();
        Book b2 = Book.builder("Book", a1, j1).id(1L).publishingYear(2002).build();
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 9")
    void testEqualsShouldReturnTrueForEqualsObjects9() {
        Author a1 = new Author(1L, "Author1");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Comment c1 = new Comment(1L, "comment");
        Author a2 = new Author(1L, "Author1");
        Jenre j2 = new Jenre(1L, "Jenre1");
        Comment c2 = new Comment(1L, "comment");
        Author a2_diff = new Author(1L, "Author2");
        Jenre j2_diff = new Jenre(2L, "Jenre1");

        Book b1 = Book.builder("Book", a1, j1).id(1L).publishingYear(2001).build();
        Book b2 = Book.builder("Book", a1, j1).id(1L).build();
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }



}
