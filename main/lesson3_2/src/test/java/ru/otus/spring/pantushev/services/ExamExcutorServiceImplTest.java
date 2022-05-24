package ru.otus.spring.pantushev.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.mockito.Mockito.*;


@SpringBootTest
@DisplayName("Тест сервиса экзекутора")
@ContextConfiguration(
        loader =  AnnotationConfigContextLoader.class
        //, classes = {MessageSource.class}
)
public class ExamExcutorServiceImplTest {
    @Configuration
    public static class ExamExcutorServiceImplTesttConfiguration {
    }

    @MockBean
    QuestionServiceImpl questionsService;

    @MockBean
    ExamCalcResultServiceImpl examCalcResultServiceImpl;

    @MockBean
    UIServiceImpl ioService;

    @MockBean
    MessageSourceServiceImpl messageSourceServiceImpl;

    @SpyBean
    ExamExecutorServiceImpl examExecutorServiceImplTest;


    @Test
    @DisplayName("Создание examExecutorService")
    public void shouldCorrectInstantiatedExamExecutorService() {
        Assertions.assertNotNull(examExecutorServiceImplTest);
    }

    @Test
    @DisplayName("examExecute: Должно вызывать oneExamExecute два раза")
    public void shouldExecuteOneExamExecute3Times() {
        when(ioService.requestYes(any()))
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        doNothing().when(ioService).showMessage(any());
        doReturn(true).when(examExecutorServiceImplTest).oneExamExecute();

        examExecutorServiceImplTest.examExecute();
        verify(examExecutorServiceImplTest, times(3)).oneExamExecute();
    }

}
