package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.Student;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ReflectionTestUtilsTest1 {

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach() {
        studentOne.setId(1);
        studentOne.setFirstname("Eric");
        studentOne.setLastname("Roby");
        studentOne.setEmailAddress("eric.roby@luv2code.com");
        studentOne.setStudentGrades(studentGrades);
    }


    @Test
    void setFieldTest() {
        ReflectionTestUtils.setField(
                studentOne,
                "firstname",
                "Grigori",
                String.class);
        System.out.println("FirstName: " + studentOne.getFirstname());
    }

    @Test
    void getIdAndFirstname() {
        String getIdAndFirstName = ReflectionTestUtils.invokeMethod(studentOne, "getIdAndFirstName");
        System.out.println(getIdAndFirstName);
        assertEquals("id 1: Eric",getIdAndFirstName);
    }

}
