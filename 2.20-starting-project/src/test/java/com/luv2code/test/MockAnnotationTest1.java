package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest1 {

    @Autowired
    ApplicationContext context;
    @Autowired
    CollegeStudent studentOne;
    @Autowired
    StudentGrades studentGrades;
    //@Mock
    @MockBean
    private ApplicationDao applicationDao;
    //@InjectMocks
    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    public void beforeEach() {
        studentOne.setFirstname("Eric");
        studentOne.setLastname("Roby");
        studentOne.setEmailAddress("eric.roby@luv2code.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @Test
    void addGradeResultsForSingleClassTest() {
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()))
                .thenReturn(99.00);
        assertEquals(99.00, applicationService.addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults()));
        verify(applicationDao, times(1)).addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults()
        );

    }

    @Test
    void findGpa() {
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults()))
                .thenReturn(99.99);
        assertEquals(99.99, applicationService.findGradePointAverage(
                studentGrades.getMathGradeResults()));
        verify(applicationDao,times(1)).findGradePointAverage(
                studentGrades.getMathGradeResults()
        );
    }

    @Test
    void checkNull(){
        ApplicationDao dao=context.getBean("applicationDao",ApplicationDao.class);
        when(dao.checkNull(null)).thenReturn(studentOne);
        assertEquals(studentOne,applicationService.checkNull(null));
        verify(dao,times(1)).checkNull(null);
    }



}





























