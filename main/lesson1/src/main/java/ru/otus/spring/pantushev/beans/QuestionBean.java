package ru.otus.spring.pantushev.beans;

public class QuestionBean {
    private int id;
    private String body;
    private String answers;

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public QuestionBean(int id, String body, String answers) {
        this.id = id;
        this.body = body;
        this.answers = answers;
    }
}


