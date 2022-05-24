package ru.otus.spring.pantushev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.pantushev.services.ExamExecutorServiceImpl;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        ExamExecutorServiceImpl examExec = context.getBean(ExamExecutorServiceImpl.class);
        examExec.ExamExecute();
    }
}
