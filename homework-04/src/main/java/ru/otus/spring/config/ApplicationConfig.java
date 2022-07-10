package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationProperties(prefix="config")
public class ApplicationConfig {

    private String testFileName;

    private int answersToPass;

    private Locale locale;

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

    public Locale getLocale() {
        return (this.locale.toString() == "") ? Locale.getDefault() : this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
