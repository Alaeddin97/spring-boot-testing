package com.luv2code.springmvc.services;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repositories.HistoryGradesDao;
import com.luv2code.springmvc.repositories.MathGradesDao;
import com.luv2code.springmvc.repositories.ScienceGradesDao;
import com.luv2code.springmvc.repositories.StudentDao;
import com.luv2code.springmvc.utils.GradeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    @Qualifier("mathGrades")
    private MathGrade mathGrade;

    @Autowired
    @Qualifier("scienceGrades")
    private ScienceGrade scienceGrade;

    @Autowired
    @Qualifier("historyGrades")
    private HistoryGrade historyGrade;

    @Autowired
    private MathGradesDao mathGradeDao;
    @Autowired
    private ScienceGradesDao scienceGradeDao;
    @Autowired
    private HistoryGradesDao historyGradeDao;

    public void createStudent(String eric, String roby, String email) {
        CollegeStudent collegeStudent = new CollegeStudent(eric, roby, email);
        collegeStudent.setId(0);
        studentDao.save(collegeStudent);
    }

    public String findByEmailAddress(String email) {
        return studentDao.findByEmailAddress(email).getEmailAddress();
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        System.out.println("id=" + id + " isPresent(): " + student.isPresent());
        return student.isPresent();
    }

    public void deleteStudent(int studentId) {
        if (checkIfStudentIsNull(studentId)) {
            studentDao.deleteById(studentId);
            mathGradeDao.deleteByStudentId(studentId);
            scienceGradeDao.deleteByStudentId(studentId);
            historyGradeDao.deleteByStudentId(studentId);
        }

    }

    public Iterable<CollegeStudent> getGradeBook() {
        return studentDao.findAll();
    }

    public boolean createGrade(double grade, int studentId, GradeType type) {
        if (!checkIfStudentIsNull(studentId))
            return false;
        if (type == GradeType.MATH) {
            mathGrade.setId(0);
            mathGrade.setGrade(grade);
            mathGrade.setStudentId(studentId);
            mathGradeDao.save(mathGrade);
            return true;
        } else if (type == GradeType.SCIENCE) {
            scienceGrade.setId(0);
            scienceGrade.setGrade(grade);
            scienceGrade.setStudentId(studentId);
            scienceGradeDao.save(scienceGrade);
            return true;
        } else if (type == GradeType.HISTORY) {
            historyGrade.setId(0);
            historyGrade.setGrade(grade);
            historyGrade.setStudentId(studentId);
            historyGradeDao.save(historyGrade);
            return true;
        }

        return false;
    }

    public int deleteGrade(int id, GradeType gradeType) {
        int studentId = 0;
        if (gradeType == GradeType.MATH) {
            Optional<MathGrade> grade = mathGradeDao.findById(id);
            if (!grade.isPresent()) {
                return studentId;
            }
            studentId = mathGradeDao.findById(id).get().getStudentId();
            mathGradeDao.deleteById(id);
        } else if (gradeType == GradeType.SCIENCE) {
            Optional<ScienceGrade> grade = scienceGradeDao.findById(id);
            if (!grade.isPresent()) {
                return studentId;
            }
            studentId = scienceGradeDao.findById(id).get().getStudentId();
            scienceGradeDao.deleteById(id);
        } else if (gradeType == GradeType.HISTORY) {
            Optional<HistoryGrade> grade = historyGradeDao.findById(id);
            if (!grade.isPresent()) {
                return studentId;
            }
            studentId = historyGradeDao.findById(id).get().getStudentId();
            historyGradeDao.deleteById(id);
        }
        return studentId;
    }

    public GradebookCollegeStudent studentInformation(int studentId) {
        if(!checkIfStudentIsNull(studentId)) {
            return null;
        }else{
        Optional<CollegeStudent> collegeStudent = studentDao.findById(studentId);
            System.out.println(collegeStudent);
        List<Grade> mathGrades = mathGradeDao.findGradeByStudentId(studentId);
            System.out.println(mathGrades);
        List<Grade> scienceGrades = scienceGradeDao.findGradeByStudentId(studentId);
            System.out.println(scienceGrades);
        List<Grade> historyGrades = historyGradeDao.findGradeByStudentId(studentId);
            System.out.println(historyGrades);
        StudentGrades studentGrades=new StudentGrades();
        studentGrades.setMathGradeResults((mathGrades));
        studentGrades.setScienceGradeResults(scienceGrades);
        studentGrades.setHistoryGradeResults(historyGrades);
        GradebookCollegeStudent gradebookCollegeStudent = new GradebookCollegeStudent(
                collegeStudent.get().getId(),
                collegeStudent.get().getFirstname(),
                collegeStudent.get().getLastname(),
                collegeStudent.get().getEmailAddress(),
                studentGrades
        );
        return gradebookCollegeStudent;
        }
    }

    public void configureStudentInformationModel(int studentId, Model m){
        GradebookCollegeStudent studentEntity = studentInformation(studentId);
        m.addAttribute("student", studentEntity);

        if (studentEntity.getStudentGrades().getMathGradeResults().size() > 0) {
            m.addAttribute("mathAverage", studentEntity.getStudentGrades().findGradePointAverage(
                    studentEntity.getStudentGrades().getMathGradeResults()
            ));
        } else {
            m.addAttribute("mathAverage", "N/A");
        }

        if (studentEntity.getStudentGrades().getScienceGradeResults().size() > 0) {
            m.addAttribute("scienceAverage", studentEntity.getStudentGrades().findGradePointAverage(
                    studentEntity.getStudentGrades().getScienceGradeResults()
            ));
        } else {
            m.addAttribute("scienceAverage", "N/A");
        }

        if (studentEntity.getStudentGrades().getHistoryGradeResults().size() > 0) {
            m.addAttribute("scienceAverage", studentEntity.getStudentGrades().findGradePointAverage(
                    studentEntity.getStudentGrades().getHistoryGradeResults()
            ));
        } else {
            m.addAttribute("scienceAverage", "N/A");
        }

    }
}










