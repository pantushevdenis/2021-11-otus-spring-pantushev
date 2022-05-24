package ru.otus.spring.pantushev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.services.ext.UIStudentResponse;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/*
    Реализация требует сервис сообщений, умеющий возвращать предопределенные строки с требованием правильности (admonition)
    questionadmonition
    yesnotamadmonition

 */
@Service
public class UIServiceImpl
    implements UIService
{
    private final Scanner scannerIn;

    private final MessageSourceServiceImpl messageSourceServiceImpl;
    private final InputStream in;
    private final PrintStream out;

    @Autowired
    public UIServiceImpl(MessageSourceServiceImpl messageSourceServiceImpl) {
        this.messageSourceServiceImpl = messageSourceServiceImpl;
        this.in = System.in;
        this.out = System.out;
        scannerIn = new Scanner(in);
    }

    @Override
    public void showMessage(String message) {
        out.println(message);
        out.println();
    }

    @Override
    public UIStudentResponse requestAnswer(String message) {
        out.println(message);
        int answer;
        while (true) {
            out.println(messageSourceServiceImpl.getMessage("questionadmonition", null));
            out.flush();
            String answerStr = scannerIn.next();
            if (answerStr.equals("q")) {
                return new UIStudentResponse(0, true);
            }
            try {
                answer = Integer.parseInt(answerStr);
                break;
            } catch (Exception e) {
            }
        }
        return new UIStudentResponse(answer, false);
    }

    @Override
    public boolean requestYes(String message) {
        out.println(message);
        out.println(messageSourceServiceImpl.getMessage("yesnotamadmonition", null));
        return scannerIn.next().equals("y");
    }
}
