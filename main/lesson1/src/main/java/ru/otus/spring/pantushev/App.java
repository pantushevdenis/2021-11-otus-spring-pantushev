package ru.otus.spring.pantushev;

import jdk.nashorn.internal.runtime.Debug;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.pantushev.beans.QuestionBean;
import ru.otus.spring.pantushev.services.QuestionService;
import ru.otus.spring.pantushev.services.QuestionServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class App
{
    final String[] args;

    public App(String[] args) {
        this.args = args;
    }

    private void execute()
            throws IOException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionServiceImpl.class);

        Iterator<QuestionBean> ib = service.getQuestionsAsIterator();
        while(ib.hasNext())
        {
            QuestionBean qb = ib.next();

            System.out.println("Question:");
            System.out.println("id: " + qb.getId());
            System.out.println("body: " + qb.getBody());
            System.out.println("answers: " + qb.getAnswers());
            System.out.println();

        }
    }


    public static void main( String[] args )
            throws IOException
    {
        App app = new App(args);
        app.execute();
    }
}
