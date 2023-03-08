package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationTestExample {

    static int counter;

    @Autowired
    CollegeStudent student;
    @Autowired
    StudentGrades studentGrades;
    @Autowired
    ApplicationContext context;
    @Value("${info.app.name}")
    String appName;
    @Value("${info.app.description}")
    String appDescription;
    @Value("${info.app.version}")
    String appVersion;
    @Value("${info.school.name}")
    String schoolName;

    @BeforeEach
    void beforeEach() {
        counter++;
        System.out.println("Testing: " + appName + " which is " + appDescription
                + " Version: " + appVersion + " Execution of test method " + counter);

        student.setFirstname("Eric");
        student.setLastname("Roby");
        student.setEmailAddress("eric.roby@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 75.5, 50.5, 80.0)));
        student.setStudentGrades(studentGrades);
    }

    @Test
    public void basicTest() {

    }

    @DisplayName("Add student grade equal")
    @Test
    public void testAddStudentGradeEqual(){
        assertEquals(306,student.getStudentGrades().addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults()
        ));
    }

    @DisplayName("Add student grade not equal")
    @Test
    public void testAddStudentGradeNotEqual(){
        assertNotEquals(0,student.getStudentGrades().addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults()
        ));
    }

    @DisplayName("Is grade greater true")
    @Test
    public void testGradeIsGreaterTrue(){
        assertTrue(student.getStudentGrades().isGradeGreater(90,89),"Should be true");
    }

    @DisplayName("Is grade greater false")
    @Test
    public void testGradeIsGreaterFalse(){
        assertFalse(student.getStudentGrades().isGradeGreater(70,89),"Should be false");
    }

    @DisplayName("Check grade not null")
    @Test
    public void testGradeIsNotNull(){
        assertNotNull(student.getStudentGrades().checkNull(
                studentGrades.getMathGradeResults()),"Should not be null");
    }

    @DisplayName("Create Student Without Grade In it")
    @Test
    public void createStudentWithoutGradeInit(){
        CollegeStudent studentTow=context.getBean("collegeStudent",CollegeStudent.class);

        studentTow.setFirstname("Aladdin");
        studentTow.setLastname("Garraoui");
        studentTow.setEmailAddress("aladin.garraoui@gmail.com");

        assertNotNull(studentTow.getFirstname());
        assertNotNull(studentTow.getLastname());
        assertNotNull(studentTow.getEmailAddress());
        assertNull(studentTow.getStudentGrades());
    }

    @DisplayName("Verify student is prototype")
    @Test
    public void verifyStudentIsPrototype(){
        CollegeStudent studentTow=context.getBean("collegeStudent",CollegeStudent.class);
        assertNotSame(student,studentTow,"Tow different objects");
    }

    @DisplayName(" Find grade point average")
    @Test
    public void findGradePointAverage(){
        CollegeStudent studentTow=context.getBean("collegeStudent",CollegeStudent.class);
        assertAll(
                ()->assertEquals(306,studentGrades.addGradeResultsForSingleClass(
                        student.getStudentGrades().getMathGradeResults()
                )),
                ()->assertEquals(76.5,studentGrades.findGradePointAverage(
                        student.getStudentGrades().getMathGradeResults()
                ))
        );
    }


}





















