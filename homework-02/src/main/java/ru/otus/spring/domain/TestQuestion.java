package ru.otus.spring.domain;

public class TestQuestion {
    public String question;
    public String answer;

    public TestQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void Ask() {
        System.out.println(question);
    }

    public boolean Check(String answer) {
        return (this.answer.compareTo(answer) == 0);
    }
}
