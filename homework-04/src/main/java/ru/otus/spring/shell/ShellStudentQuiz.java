package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.StudentTestService;

@ShellComponent
public class ShellStudentQuiz {
    private final StudentTestService studentTestService;

    @Autowired
    ShellStudentQuiz(StudentTestService studentTestService) {
        this.studentTestService = studentTestService;
    }

    @ShellMethod("Run Student's Quiz Application")
    public void run(@ShellOption String testName) {
        studentTestService.run(testName);
    }
}
