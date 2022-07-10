package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.Vector;

public class StudentTest {
    private ArrayList<TestQuestion> questions;
    private String name;
    private Person student;

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

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }
}
