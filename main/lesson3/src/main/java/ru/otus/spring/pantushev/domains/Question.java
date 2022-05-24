package ru.otus.spring.pantushev.domains;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Question {
    private final QuestionHead head;
    private final int idCorrectAnswer;
    private final ArrayList<Answer> answers = new ArrayList<>();

    public int getId() {
        return head.getId();
    }
    public String getBody() { return head.getBody(); }
}


