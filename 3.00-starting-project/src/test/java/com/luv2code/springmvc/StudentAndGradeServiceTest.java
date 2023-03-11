package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repositories.StudentDAO;
import com.luv2code.springmvc.services.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class StudentAndGradeServiceTest {

    @Autowired
    CollegeStudent student;
    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    private void beforeEach(){
        jdbcTemplate.execute("insert into student (id,firstName,lastName,email_address)" +
                "values(1,'chad','darby','chad.darby@luv2code_school.com')");
    }

    @AfterEach
    private void afterEach(){
        jdbcTemplate.execute("DELETE FROM student");
    }

    @Test
    public void createStudentService(){
        studentService.createStudent("Eric","Roby","eric.roby@luv2code_school.com");
        student=studentDAO.findByEmailAddress("eric.roby@luv2code_school.com");
        assertEquals("eric.roby@luv2code_school.com",student.getEmailAddress(),
                "Emails should be same");

    }

    @Test
    public void addStudentEntitiesInDatabase(){
        assertTrue(studentService.checkIfStudentIsNull(1));
        assertFalse(studentService.checkIfStudentIsNull(2));
    }

    @Test
    public void deleteStudent(){
        studentService.deleteStudent(1);
        assertFalse(studentService.checkIfStudentIsNull(1));
    }

    @Sql("/insertStudentData.sql")
    @Test
    public void getGradeBookService(){
        Iterable<CollegeStudent>students= studentService.getGradeBook();

        List<CollegeStudent>collegeStudents=new ArrayList<>();
        for(CollegeStudent student:students){
            collegeStudents.add(student);
        }

        assertEquals(5,collegeStudents.size());
    }
}
