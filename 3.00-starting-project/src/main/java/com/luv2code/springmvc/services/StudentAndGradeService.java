package com.luv2code.springmvc.services;

import com.luv2code.springmvc.repositories.StudentDAO;
import com.luv2code.springmvc.models.CollegeStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDAO studentDAO;
    public void createStudent(String eric, String roby, String email) {
        CollegeStudent collegeStudent=new CollegeStudent(eric,roby,email);
        collegeStudent.setId(0);
        studentDAO.save(collegeStudent);
    }
    public String findByEmailAddress(String email){
        return studentDAO.findByEmailAddress(email).getEmailAddress();
    }

    public boolean checkIfStudentIsNull(int i) {
        Optional<CollegeStudent> student=studentDAO.findById(i);
        System.out.println("id="+i+" isPresent(): "+student.isPresent());
        return student.isPresent();
    }

    public void deleteStudent(int i) {
        studentDAO.deleteById(i);
    }

    public Iterable<CollegeStudent> getGradeBook() {
        return studentDAO.findAll();
    }
}
