package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repositories.StudentDao;
import com.luv2code.springmvc.services.StudentAndGradeService1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class StudentAndGradeServiceTest1 {

    @Autowired
    private CollegeStudent student;
    @Autowired
    private StudentAndGradeService1 studentService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init(){
        jdbcTemplate.execute(
                "insert into student (id,firstname,lastname,email_address)" +
                        "values(1,'Grigori','Perlman','grigori.perlman@gmail.com')"
        );
    }
    @AfterEach
    void cleanup(){
        jdbcTemplate.execute("delete from student");
    }

    @Test
    void createStudentTest() {
        CollegeStudent student = new CollegeStudent(
                "Grigori",
                "Perlman",
                "grigori.perlman@gmail.com");

        studentService.createStudent(student);
        assertEquals(
                "grigori.perlman@gmail.com",
                studentDao.findByEmailAddress("grigori.perlman@gmail.com").getEmailAddress());

    }

    @Test
    void getStudentTest(){
        CollegeStudent studentOne = (CollegeStudent) studentService.findByEmail("grigori.perlman@gmail.com");
        assertEquals("Grigori",studentOne.getFirstname());
    }

    @Sql("/insertStudentData.sql")
    @Test
    void getGradeBookTest(){
        Iterable<CollegeStudent> students=studentService.getStudents();
        List<CollegeStudent>collegeStudents=new ArrayList<>();
        for (CollegeStudent student:
             students) {
            collegeStudents.add(student);
        }
        assertEquals(5, collegeStudents.size());
    }

}
