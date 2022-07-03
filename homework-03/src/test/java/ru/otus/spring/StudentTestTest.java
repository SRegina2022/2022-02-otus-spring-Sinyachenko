package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.dao.StudentTestDaoSimple;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.StudentTest;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.StringReadService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentTestTest {

    @MockBean
    @Autowired
    private StringReadService stringReadService;

    @Autowired
    private PersonService personService;

    @Autowired
    private StudentTestDaoSimple dao;


    @DisplayName("Constructor")
    @Test
    void correctConstructor() {
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

    @DisplayName("Person Service: getPerson()")
    @Test
    void personServiceGetPerson() {
        Mockito.when(stringReadService.read("firstName")).thenReturn("qwerty");
        Mockito.when(stringReadService.read("lastName")).thenReturn("asdf");
        Person person = personService.getPerson();
        assertEquals(person.getFirstName(), "qwerty");
        assertEquals(person.getLastName(), "asdf");
    }
}
