package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.dao.StudentTestDaoSimple;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.StudentTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentTestTest {
    @DisplayName("Constructor")
    @Test
    void correctConstructor() {
        ApplicationConfig applicationConfig = new ApplicationConfig();

        applicationConfig.setTestFileName("test-questions.csv");

        StudentTestDaoSimple dao = new StudentTestDaoSimple(applicationConfig);
        Person person = new Person("John", "Dow");
        StudentTest test = dao.getTest("Simple Test", person);
        assertAll("Check Name and questions count",
                () -> assertEquals("Simple Test", test.getName()),
                () -> assertEquals(4, test.getQuestions().size()));
    }

    @DisplayName("Name Setter")
    @Test
    void setTestName() {
        String testName = "Hard Test";
        StudentTest test = new StudentTest("Simple Test");
        test.setName(testName);
        assertEquals(testName, test.getName());
    }
}
