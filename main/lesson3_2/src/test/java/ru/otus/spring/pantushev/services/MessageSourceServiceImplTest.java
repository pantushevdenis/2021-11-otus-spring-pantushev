package ru.otus.spring.pantushev.services;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.otus.spring.pantushev.config.UserInterfaceConfig;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;


import java.util.Locale;

@SpringBootTest
@ContextConfiguration(
        loader =  AnnotationConfigContextLoader.class
        //, classes = {MessageSource.class}

)
@DisplayName("Тест сервиса ресурсов сообщений")
public class MessageSourceServiceImplTest {
    final static String code = "test";
    final static String badCode = "qqqqqqqq";
    final static Locale localeEn = Locale.forLanguageTag("en-EN");
    final static Locale localeRu = Locale.forLanguageTag("ru-RU");
    final static Locale localeDe = Locale.forLanguageTag("de-DE");
    final static String responceEn = "HELLO WORLD";
    final static String responceRu = "HELLO МИР";

    @Configuration
    public static class MessageSourceServiceImplTestConfiguration {
        @Bean(name="messageSourceMock")
        public MessageSource messageSourceMock() {
            MessageSource msMock = Mockito.mock(MessageSource.class);

            // В @BeforeEach задание мока работает неправильно. invocation вызывается когда не нужно и ему передаются одни null, что вызывает throw.
            when(msMock.getMessage(any(), any(),any() )).thenAnswer(
                    invocation -> {
                        String code = invocation.getArgument(0, String.class);
                        Object args = invocation.getArgument(1);
                        Locale locale = invocation.getArgument(2, Locale.class);

                        if (StringUtils.equals(code, MessageSourceServiceImplTest.code)) {
                            if (locale.getLanguage().equals(MessageSourceServiceImplTest.localeEn.getLanguage())) {
                                return MessageSourceServiceImplTest.responceEn;
                            }
                            else if (locale.getLanguage().equals(MessageSourceServiceImplTest.localeRu.getLanguage())) {
                                return MessageSourceServiceImplTest.responceRu;
                            }
                            else {
                                throw new NoSuchMessageException("message not found");
                            }
                        }
                        else {
                            throw new NoSuchMessageException("message not found");
                        }
                    }
            );

            return msMock;
        }

        @Bean(name="userInterfaceConfigTestEn")
        public UserInterfaceConfig userInterfaceConfigTestEn() {
            UserInterfaceConfig uic = new UserInterfaceConfig();
            uic.setLocale("en-EN");
            return uic;
        }

        @Bean(name="userInterfaceConfigTestRu")
        public UserInterfaceConfig userInterfaceConfigTestRu() {
            UserInterfaceConfig uic = new UserInterfaceConfig();
            uic.setLocale("ru-RU");
            return uic;
        }

        @Bean(name="userInterfaceConfigTestDe")
        public UserInterfaceConfig userInterfaceConfigTestDe() {
            UserInterfaceConfig uic = new UserInterfaceConfig();
            uic.setLocale("de-DE");
            return uic;
        }



        /*
        бины для разных случаев необходимы потому, что происходит сложная инициализация бина с участимем userInterfaceConfigTestEn.
         */
        @Bean(name= "messageSourceServiceImplTestEn")
        public MessageSourceServiceImpl messageSourceServiceImplTestEn(MessageSource messageSourceMock, UserInterfaceConfig userInterfaceConfigTestEn) {
            MessageSourceServiceImpl res = new MessageSourceServiceImpl(messageSourceMock, userInterfaceConfigTestEn);
            return res;
        }

        @Bean(name= "messageSourceServiceImplTestRu")
        public MessageSourceServiceImpl messageSourceServiceImplTestRu(MessageSource messageSourceMock, UserInterfaceConfig userInterfaceConfigTestRu) {
            MessageSourceServiceImpl res = new MessageSourceServiceImpl(messageSourceMock, userInterfaceConfigTestRu);
            return res;
        }

        @Bean(name= "messageSourceServiceImplTestDe")
        public MessageSourceServiceImpl messageSourceServiceImplTestDe(MessageSource messageSourceMock, UserInterfaceConfig userInterfaceConfigTestDe) {
            MessageSourceServiceImpl res = new MessageSourceServiceImpl(messageSourceMock, userInterfaceConfigTestDe);
            return res;
        }


    }


    @Autowired
    @Qualifier("messageSourceServiceImplTestEn")
    MessageSourceService messageSourceServiceImplTestEn;

    @Autowired
    @Qualifier("messageSourceServiceImplTestRu")
    MessageSourceService messageSourceServiceImplTestRu;

    @Autowired
    @Qualifier("messageSourceServiceImplTestDe")
    MessageSourceService messageSourceServiceImplTestDe;


    @Test
    @DisplayName("Создается для EN без ошибок")
    public void shouldCorrectInstantiatedForEn() {
        Assertions.assertNotNull(messageSourceServiceImplTestEn);
    }

    @Test
    @DisplayName("En: Содержит локаль en-EN")
    public void shoulLocaleEn() {
        Assertions.assertEquals(Locale.forLanguageTag("en-EN"), messageSourceServiceImplTestEn.getLocale());
    }

    @Test
    @DisplayName("На запрос строки test с локалью en-EN выдает HELLO WORLD")
    public void shouldReturnTestEnToHELLO() {
        String res = messageSourceServiceImplTestEn.getMessage(code, null);
        Assertions.assertEquals(responceEn, res);
    }

    @Test
    @DisplayName("На запрос отсутствующей строки с локалью en-EN выбрасывает NoSuchMessageException")
    public void shouldExceptionForBadCodeEn() {
        Assertions.assertThrows(
                NoSuchMessageException.class,
                () -> {
                    String res = messageSourceServiceImplTestDe.getMessage(badCode, null);
                }
        );
    }


    @Test
    @DisplayName("На запрос строки test с локалью ru-RU выдает ПРИВЕТ МИР")
    public void shouldReturnTestRuToHELLO() {
        String res = messageSourceServiceImplTestRu.getMessage(code, null);
        Assertions.assertEquals(responceRu, res);
    }

    @Test
    @DisplayName("На запрос строки test с локалью de-DE Выбрасывает исключение")
    public void shouldReturnTestDeToHELLO() {
        Assertions.assertThrows(
                NoSuchMessageException.class,
                () -> {
                    String res = messageSourceServiceImplTestDe.getMessage(code, null);
                }
        );
    }

}
