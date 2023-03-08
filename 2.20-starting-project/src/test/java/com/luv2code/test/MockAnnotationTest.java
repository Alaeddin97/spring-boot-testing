package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext context;
    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    //@Mock
    @MockBean
    ApplicationDao applicationDao;

    //@InjectMocks
    @Autowired
    ApplicationService applicationService;

    @BeforeEach
    public void beforeEach(){
        studentOne.setFirstname("Eric");
        studentOne.setLastname("Roby");
        studentOne.setEmailAddress("eric.roby@luv2code.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @DisplayName("When & Verify")
    @Test
    public void assertEqualsTestAddGrades(){
        when(applicationDao.addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults())).thenReturn(100.0);

        assertEquals(100.0,applicationService.addGradeResultsForSingleClass(
                studentOne.getStudentGrades().getMathGradeResults()
        ));

        verify(applicationDao,times(1)).addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults());

    }

    @DisplayName("Find Gpa")
    @Test
    public void assertEqualsTestFindGpa(){
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults()))
                .thenReturn(88.31);
        assertEquals(88.31,applicationService.findGradePointAverage(
                studentGrades.getMathGradeResults()));

    }


    @DisplayName("Not null")
    @Test
    public void assertTestNotNull(){
        when(applicationDao.checkNull(studentGrades.getMathGradeResults()))
                .thenReturn(true);
        assertNotNull(applicationService.checkNull(
                studentOne.getStudentGrades().getMathGradeResults()),"this should not be null");

    }

    @DisplayName("Throw exception")
    @Test
    public void testThrowException(){
        CollegeStudent studentNull=context.getBean("collegeStudent",CollegeStudent.class);

        when(applicationDao.checkNull(studentNull)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class,()->applicationService.checkNull(
                applicationDao.checkNull(studentNull)),"should not be null");
    }

    @DisplayName("Throw executive calls")
    @Test
    public void executiveCalls(){
        CollegeStudent studentNull=context.getBean("collegeStudent",CollegeStudent.class);

        when(applicationDao.checkNull(studentNull)).
                thenThrow(new RuntimeException())
                .thenReturn("Do not throw exception");

        assertThrows(RuntimeException.class,()->applicationService.checkNull(
                applicationDao.checkNull(studentNull)),"should not be null");

        assertSame("Do not throw exception",applicationService.checkNull(studentNull),
                "should be null");
    }
}
























