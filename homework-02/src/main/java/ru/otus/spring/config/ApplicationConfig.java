package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="config")
public class ApplicationConfig {
    @Value("test-questions.csv")
    private String testFileName;

    @Value("3")
    private int answersToPass;

    public String getTestFileName() {
        return testFileName;
    }

    public void setTestFileName(String testFileName) {
        this.testFileName = testFileName;
    }

    public int getAnswersToPass() {
        return answersToPass;
    }

    public void setAnswersToPass(int answersToPass) {
        this.answersToPass = answersToPass;
    }
}
