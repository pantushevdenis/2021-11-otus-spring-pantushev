package ru.otus.spring.pantushev.services;

import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.pantushev.domain.mongodb.Jenre;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DisplayName("Простое тестирование вывода без возбуждения исключений")
@ExtendWith(MockitoExtension.class)
class PrintCollectionServiceImplTest {
    @InjectMocks
    private PrintCollectionServiceImpl printCollectionService;

    private static final String ID_1 = ObjectId.get().toString();
    private static final String ID_2 = ObjectId.get().toString();
    private static final String FULL_NAME_1 = "FullName1";
    private static final String FULL_NAME_2 = "FullName2";
    private static final String SHORT_NAME_1 = "ShortName1";
    private static final String SHORT_NAME_2 = "ShortName2";
    private static Date DATE_1;


    static {
        try {
            DATE_1 = DateUtils.parseDate("01-02-2001", "dd-mm-yyyy");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Должно выполниться без исклюений")
    void shouldPrintCollection() {
        List<Jenre> l = Arrays.asList(
            new Jenre(ID_1, FULL_NAME_1, SHORT_NAME_1),
            new Jenre(ID_2, FULL_NAME_2, SHORT_NAME_2)
        );

        printCollectionService.printCollection(l, "Jenres");
    }
}