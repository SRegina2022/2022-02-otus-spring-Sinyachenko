package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.StudentTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentTestTest {
    @DisplayName("Constructor")
    @Test
    void correctConstructor() {
        StudentTest test = new StudentTest("Simple Test");
        assertAll("Check Name and questions count",
                () -> assertEquals("Simple Test", test.getName()),
                () -> assertEquals(4, test.getQuestions().size()));
    }

    @DisplayName("Name Setter")
    @Test
    void setTestName() {
        String  testName = "Hard Test";
        StudentTest test = new StudentTest("Simple Test");
        test.setName(testName);
        assertEquals(testName, test.getName());
    }
}
