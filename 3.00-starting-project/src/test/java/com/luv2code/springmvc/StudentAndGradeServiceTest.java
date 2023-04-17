package com.luv2code.springmvc;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repositories.HistoryGradesDao;
import com.luv2code.springmvc.repositories.MathGradesDao;
import com.luv2code.springmvc.repositories.ScienceGradesDao;
import com.luv2code.springmvc.repositories.StudentDao;
import com.luv2code.springmvc.services.StudentAndGradeService;
import com.luv2code.springmvc.utils.GradeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Profile("!tester")
@TestPropertySource("/application.properties")
@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class StudentAndGradeServiceTest {

    @Autowired
    CollegeStudent student;
    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MathGradesDao mathGradeDao;

    @Autowired
    private ScienceGradesDao scienceGradeDao;

    @Autowired
    private HistoryGradesDao historyGradeDao;

    @Autowired
    @Qualifier("mathGrades")
    private MathGrade mathGrades;

    @Autowired
    @Qualifier("scienceGrades")
    private ScienceGrade scienceGrades;

    @Autowired
    @Qualifier("historyGrades")
    private HistoryGrade historyGrades;

    @Value("${sql.script.create.student}")
    private String sqlAddStudent;
    @Value("${sql.script.create.math.grade}")
    private String sqlAddMathGrade;
    @Value("${sql.script.create.science.grade}")
    private String sqlAddScienceGrade;
    @Value("${sql.script.create.history.grade}")
    private String sqlAddHistoryGrade;
    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;
    @Value("${sql.script.delete.math.grade}")
    private String sqlDeleteMathGrade;
    @Value("${sql.script.delete.science.grade}")
    private String sqlDeleteScienceGrade;
    @Value("${sql.script.delete.history.grade}")
    private String sqlDeleteHistoryGrade;

    @BeforeEach
    public void beforeEach() {
        jdbcTemplate.execute(sqlAddStudent);
        jdbcTemplate.execute(sqlAddMathGrade);
        jdbcTemplate.execute(sqlAddScienceGrade);
        jdbcTemplate.execute(sqlAddHistoryGrade);
    }

    @AfterEach
    public void afterEach() {
        jdbcTemplate.execute(sqlDeleteStudent);
        jdbcTemplate.execute(sqlDeleteMathGrade);
        jdbcTemplate.execute(sqlDeleteScienceGrade);
        jdbcTemplate.execute(sqlDeleteHistoryGrade);
    }

    @Test
    public void createStudentService() {
        studentService.createStudent("Eric", "Roby", "eric.roby@luv2code_school.com");
        student = studentDao.findByEmailAddress("eric.roby@luv2code_school.com");
        assertEquals("eric.roby@luv2code_school.com", student.getEmailAddress(),
                "Emails should be same");

    }

    @Test
    public void addStudent() {
        assertTrue(studentService.checkIfStudentIsNull(1));
        assertFalse(studentService.checkIfStudentIsNull(2));
    }

    @Test
    public void deleteStudent() {
        Optional<CollegeStudent> student = studentDao.findById(1);
        Optional<MathGrade> mathGrade = mathGradeDao.findById(1);
        Optional<ScienceGrade> scienceGrade = scienceGradeDao.findById(1);
        Optional<HistoryGrade> historyGrade = historyGradeDao.findById(1);

        assertTrue(student.isPresent());
        assertTrue(mathGrade.isPresent());
        assertTrue(scienceGrade.isPresent());
        assertTrue(historyGrade.isPresent());

        studentService.deleteStudent(1);

        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);
        mathGrade = mathGradeDao.findById(1);
        scienceGrade = scienceGradeDao.findById(1);
        historyGrade = historyGradeDao.findById(1);

        assertFalse(deletedCollegeStudent.isPresent());
        assertFalse(mathGrade.isPresent());
        assertFalse(scienceGrade.isPresent());
        assertFalse(historyGrade.isPresent());

    }

    @Sql("/insertStudentData.sql")
    @Test
    public void getGradeBookService() {
        Iterable<CollegeStudent> students = studentService.getGradeBook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();
        for (CollegeStudent student : students) {
            collegeStudents.add(student);
        }

        assertEquals(5, collegeStudents.size());
    }

    @Test
    public void createGradeService() {
        // create grade
        assertTrue(studentService.createGrade(80.8, 1, GradeType.MATH));
        // get grade
        List<Grade> mathGrades = mathGradeDao.findGradeByStudentId(1);
        // assert
        assertTrue(mathGrades.iterator().hasNext());

        assertTrue(studentService.createGrade(80.8, 1, GradeType.SCIENCE));
        List<Grade> scienceGrades = scienceGradeDao.findGradeByStudentId(1);
        assertTrue(scienceGrades.iterator().hasNext());

        assertTrue(studentService.createGrade(80.8, 1, GradeType.HISTORY));
        List<Grade> historyGrades = historyGradeDao.findGradeByStudentId(1);
        assertTrue(historyGrades.iterator().hasNext());

    }

    @Test
    public void createGradeServiceReturnFalse() {
        GradeType gradeType = GradeType.GEOGRAPHY;
        int id = 1;
        double grade = 555.5;
        GradeType[] gradeTypes = {GradeType.MATH, GradeType.SCIENCE, GradeType.HISTORY};

        if (Arrays.binarySearch(gradeTypes, gradeType) < 0 ||
                !studentService.checkIfStudentIsNull(id) ||
                (grade < 0 || grade > 100)
        ) {
            assertFalse(studentService.createGrade(grade, id, GradeType.GEOGRAPHY));
        }
    }

    @Test
    public void getNumberGradesGradeService() {

        Iterable<MathGrade> grades = mathGradeDao.findAll();
        List<MathGrade> mathGrades = new ArrayList<>();
        for (MathGrade mathGrade : grades) {
            mathGrades.add(mathGrade);
        }
        assertEquals(mathGrades.size(), 1);
    }

    @Test
    public void deleteGradeService() {
        int id = 1;
        assertEquals(studentService.deleteGrade(id, GradeType.MATH), id);
        assertEquals(studentService.deleteGrade(id, GradeType.SCIENCE), id);
        assertEquals(studentService.deleteGrade(id, GradeType.HISTORY), id);
    }

    @Test
    public void deleteServiceReturnStudentIdOfZero() {
        int gradeId1 = 1;
        int gradeId2 = 5;
        assertEquals(studentService.deleteGrade(gradeId1, GradeType.GEOGRAPHY), 0);
        assertEquals(studentService.deleteGrade(gradeId2, GradeType.HISTORY), 0);
    }

    @Test
    public void getStudentInformation(){
        GradebookCollegeStudent gradebookCollegeStudent=studentService.studentInformation(1);

        assertNotNull(gradebookCollegeStudent);
        assertEquals(gradebookCollegeStudent.getId(),1);
<<<<<<< HEAD
        assertEquals(gradebookCollegeStudent.getFirstname(),"Chad");
        assertEquals(gradebookCollegeStudent.getLastname(),"Darby");
        assertEquals(gradebookCollegeStudent.getEmailAddress(),"chad.darby@luv2code_school.com");
       /* assertEquals(1, gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size());
=======
        assertEquals(gradebookCollegeStudent.getFirstname(),"Eric");
        assertEquals(gradebookCollegeStudent.getLastname(),"Roby");
        assertEquals(gradebookCollegeStudent.getEmailAddress(),"eric.roby@luv2code_school.com");
        assertEquals(1, gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size());
>>>>>>> c59c0c88f3cc543bfd66ea1bded6d36f59b14b32
        assertEquals(1, gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size());
        assertEquals(1, gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size());*/

    }

    @Test
    public void studentInformationNotFound(){
        GradebookCollegeStudent gradebookCollegeStudent=studentService.studentInformation(5);
        assertNull(gradebookCollegeStudent);
    }


}


































