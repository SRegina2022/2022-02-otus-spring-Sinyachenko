package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.Scanner;

import ru.otus.spring.domain.TestQuestion;

public class StudentTest {
    private ArrayList<TestQuestion> questions;
    private String name;

    public StudentTest(String name) {
        this.name = name;
    }

    public void setQuestions(ArrayList<TestQuestion> questions) {
        this.questions = questions;
    }

    public ArrayList<TestQuestion> getQuestions() {
        return questions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
