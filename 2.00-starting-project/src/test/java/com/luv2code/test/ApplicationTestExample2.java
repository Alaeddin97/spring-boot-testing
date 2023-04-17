package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.Student;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationTestExample2 {
    @Autowired
    private Student student;
    @Autowired
    private CollegeStudent collegeStudent;
    @Autowired
    private StudentGrades studentGrades;
    @Autowired
    private ApplicationContext context;

    @Value("${info.school.name}")
    private String schoolName;
    @Value("${info.app.name}")
    private String appName;
    @Value("${info.app.description}")
    private String appDesc;
    @Value("${info.app.version}")
    private String appVersion;


    @BeforeEach
    void initAll() {
        studentGrades.setMathGradeResults(
                Arrays.asList(99.10, 99.99, 100.0)
        );
        collegeStudent.setFirstname("Grigori");
        collegeStudent.setLastname("Perlman");
        collegeStudent.setEmailAddress("grigori.perlman@gmail.com");
        collegeStudent.setStudentGrades(studentGrades);

        System.out.println(
                "School name: "+ schoolName +
                "\nApp name: " + appName +
                "\nApp description: " + appDesc +
                "\nApp version: " + appVersion
        );
    }

    @Test
    void basicTest(){

    }

    @Test
    @DisplayName("Student information test")
    void studentInformationTest(){
        String expected="Grigori Perlman grigori.perlman@gmail.com";
        String Unexpected="Grigori Perlman: email: grigori.perlman@gmail.com";
        assertEquals(expected,collegeStudent.studentInformation(),"This must pass");
        assertNotEquals(Unexpected,collegeStudent.studentInformation(),"This must pass");
    }

    @Test
    @DisplayName("Student scope test")
    void studentScopeTest(){
        CollegeStudent student1 =context.getBean("collegeStudent",CollegeStudent.class);
        assertNotSame(student1,collegeStudent,"Not the same"); //prototype scope
        //assertSame(student1,collegeStudent,"This not the same"); //prototype scope
    }

}






















