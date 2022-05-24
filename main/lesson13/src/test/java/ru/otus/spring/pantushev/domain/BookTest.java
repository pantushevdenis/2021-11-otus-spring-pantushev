package ru.otus.spring.pantushev.domain;


import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Домен Book")
public class BookTest {
    /*
    @Test
    @DisplayName("Правильно создаваться конструктором со всеми параметрами без identity")
    void shouldCreateOnRequiredArgsConstructor() {
        Book b = new Book("Book1", new Author(new ObjectId(), ""), new Jenre(new ObjectId(), ""), 2001);
    }

    @Test
    @DisplayName("Правильно создаваться конструктором без обязательных параметров без identity")
    void shouldCreateOnRequiredArgsConstructorWithNull () {
        Book b = new Book("Book1", new Author(new ObjectId(), ""), new Jenre(new ObjectId(), ""), null);
    }

    @Test
    @DisplayName("Правильно создаваться конструктором со всеми параметрами с identity")
    void shouldCreateOnAllArgsConstructor() {
        Book b = new Book(new ObjectId(), "Book1", new Author(new ObjectId(), ""), new Jenre(new ObjectId(), ""), 2001);
    }

    @Test
    @DisplayName("Правильно создаваться конструктором без обязательных параметров с identity")
    void shouldCreateOnAllArgsConstructorWithNull() {
        Book b = new Book(new ObjectId(), "Book1", new Author(new ObjectId(), ""), new Jenre(new ObjectId(), ""), null);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 1")
    void testEqualsShouldReturnTrueForEqualsObjects1() {
        ObjectId aId1 = new ObjectId();
        Author a1 = new Author(aId1, "Author1");

        ObjectId jId1 = new ObjectId();
        ObjectId jId2 = new ObjectId();

        Jenre j1 = new Jenre(jId1, "Jenre1");
        Comment c1 = new Comment(new ObjectId(), "comment");
        Jenre j2 = new Jenre(jId1, "Jenre1");
        Comment c2 = new Comment(new ObjectId(), "comment");
        Jenre j2_diff = new Jenre(jId2, "Jenre1");
        Comment c2_diff = new Comment(new ObjectId(), "comment");

        Book b1 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        Book b2 = new Book(new ObjectId(), "Book", a1, j1, 2001, Arrays.asList(c1));
        assertTrue(b1.equals(b2));
        assertEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 2")
    void testEqualsShouldReturnTrueForEqualsObjects2() {
        ObjectId jId1 = new ObjectId();
        ObjectId jId2 = new ObjectId();

        ObjectId aId1 = new ObjectId();

        Author a1 = new Author(aId1, "Author1");
        Jenre j1 = new Jenre(jId1, "Jenre1");
        Comment c1 = new Comment(new ObjectId(), "comment");
        Jenre j2 = new Jenre(jId1, "Jenre1");
        Comment c2 = new Comment(new ObjectId(), "comment");
        Jenre j2_diff = new Jenre(jId2, "Jenre1");
        Comment c2_diff = new Comment(new ObjectId(), "comment");

        Book b1 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        Book b2 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 3")
    void testEqualsShouldReturnTrueForEqualsObjects3() {
        ObjectId jId1 = new ObjectId();
        ObjectId jId2 = new ObjectId();
        ObjectId aId1 = new ObjectId();

        Author a1 = new Author(aId1, "Author1");
        Jenre j1 = new Jenre(jId1, "Jenre1");
        Comment c1 = new Comment(new ObjectId(), "comment");
        Jenre j2 = new Jenre(jId1, "Jenre1");
        Comment c2 = new Comment(new ObjectId(), "comment");
        Jenre j2_diff = new Jenre(jId2, "Jenre1");
        Comment c2_diff = new Comment(new ObjectId(), "comment");

        Book b1 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        Book b2 = new Book(new ObjectId(), "Book2", a1, j1, 2001);
        assertFalse(b1.equals(32_2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 4")
    void testEqualsShouldReturnTrueForEqualsObjects4() {
        ObjectId aId1 = new ObjectId();
        ObjectId jId1 = new ObjectId();
        ObjectId jId2 = new ObjectId();

        Author a1 = new Author(aId1, "Author1");
        Jenre j1 = new Jenre(jId1, "Jenre1");
        Comment c1 = new Comment(new ObjectId(), "comment");
        Author a2 = new Author(aId1, "Author1");
        Jenre j2 = new Jenre(jId1, "Jenre1");
        Comment c2 = new Comment(new ObjectId(), "comment");
        Author a1_diff = new Author(aId1, "Author2");
        Jenre j2_diff = new Jenre(jId2, "Jenre1");

        Book b1 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        Book b2 = new Book(new ObjectId(), "Book", a2, j1, 2001);
        assertTrue(b1.equals(b2));
        assertEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 5")
    void testEqualsShouldReturnTrueForEqualsObjects5() {
        ObjectId aId1 = new ObjectId();
        ObjectId jId1 = new ObjectId();
        ObjectId jId2 = new ObjectId();

        Author a1 = new Author(aId1, "Author1");
        Jenre j1 = new Jenre(jId1, "Jenre1");
        Comment c1 = new Comment(new ObjectId(), "comment");
        Author a2 = new Author(aId1, "Author1");
        Jenre j2 = new Jenre(jId1, "Jenre1");
        Comment c2 = new Comment(new ObjectId(), "comment");
        Author a2_diff = new Author(aId1, "Author2");
        Jenre j2_diff = new Jenre(jId2, "Jenre1");

        Book b1 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        Book b2 = new Book(new ObjectId(), "Book", a2_diff, j1, 2001);
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 6")
    void testEqualsShouldReturnTrueForEqualsObjects6() {
        ObjectId aId1 = new ObjectId();
        ObjectId jId1 = new ObjectId();
        ObjectId jId2 = new ObjectId();

        Author a1 = new Author(aId1, "Author1");
        Jenre j1 = new Jenre(jId1, "Jenre1");
        Comment c1 = new Comment(new ObjectId(), "comment");
        Author a2 = new Author(aId1, "Author1");
        Jenre j2 = new Jenre(jId1, "Jenre1");
        Comment c2 = new Comment(new ObjectId(), "comment");
        Author a1_diff = new Author(aId1, "Author2");
        Jenre j2_diff = new Jenre(jId2, "Jenre1");

        Book b1 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        Book b2 = new Book(new ObjectId(), "Book", a1, j2, 2001);
        assertTrue(b1.equals(b2));
        assertEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 7")
    void testEqualsShouldReturnTrueForEqualsObjects7() {
        ObjectId aId1 = new ObjectId();
        ObjectId jId1 = new ObjectId();
        ObjectId jId2 = new ObjectId();

        Author a1 = new Author(aId1, "Author1");
        Jenre j1 = new Jenre(jId1, "Jenre1");
        Comment c1 = new Comment(new ObjectId(), "comment");
        Author a2 = new Author(aId1, "Author1");
        Jenre j2 = new Jenre(jId1, "Jenre1");
        Comment c2 = new Comment(new ObjectId(), "comment");
        Author a2_diff = new Author(aId1, "Author2");
        Jenre j2_diff = new Jenre(jId2, "Jenre1");

        Book b1 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        Book b2 = new Book(new ObjectId(), "Book", a1, j2_diff, 2001);
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 8")
    void testEqualsShouldReturnTrueForEqualsObjects8() {
        ObjectId aId1 = new ObjectId();
        ObjectId jId1 = new ObjectId();
        ObjectId jId2 = new ObjectId();

        Author a1 = new Author(aId1, "Author1");
        Jenre j1 = new Jenre(jId1, "Jenre1");
        Comment c1 = new Comment(new ObjectId(), "comment");
        Author a2 = new Author(aId1, "Author1");
        Jenre j2 = new Jenre(jId1, "Jenre1");
        Comment c2 = new Comment(new ObjectId(), "comment");
        Author a2_diff = new Author(aId1, "Author2");
        Jenre j2_diff = new Jenre(jId2, "Jenre1");

        Book b1 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        Book b2 = new Book(new ObjectId(), "Book", a1, j1, 2002);
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Эквивалентность за исключнием поля списка комментариев - 9")
    void testEqualsShouldReturnTrueForEqualsObjects9() {
        ObjectId aId1 = new ObjectId();
        ObjectId jId1 = new ObjectId();
        ObjectId jId2 = new ObjectId();

        Author a1 = new Author(aId1, "Author1");
        Jenre j1 = new Jenre(jId1, "Jenre1");
        Comment c1 = new Comment(new ObjectId(), "comment");
        Author a2 = new Author(aId1, "Author1");
        Jenre j2 = new Jenre(jId1, "Jenre1");
        Comment c2 = new Comment(new ObjectId(), "comment");
        Author a2_diff = new Author(aId1, "Author2");
        Jenre j2_diff = new Jenre(jId2, "Jenre1");

        Book b1 = new Book(new ObjectId(), "Book", a1, j1, 2001);
        Book b2 = new Book(new ObjectId(), "Book", a1, j1, null);
        assertFalse(b1.equals(b2));
        assertNotEquals(b1, b2);
    }



*/
}
