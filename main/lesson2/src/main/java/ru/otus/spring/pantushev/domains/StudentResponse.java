package ru.otus.spring.pantushev.domains;

public class StudentResponse {
    private final int idQuestion;
    private final int idAnswer;

    public int getIdQuestion() {
        return idQuestion;
    }

    public int getIdAnswer() {
        return idAnswer;
    }

    public StudentResponse(int idQuestion, int idAnswer) {
        this.idQuestion = idQuestion;
        this.idAnswer = idAnswer;
    }
}
