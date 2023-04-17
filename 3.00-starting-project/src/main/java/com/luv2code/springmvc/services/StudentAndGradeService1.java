package com.luv2code.springmvc.services;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.models.Student;
import com.luv2code.springmvc.repositories.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAndGradeService1 {

    @Autowired
    private StudentDao studentDao;

    public void createStudent(CollegeStudent student){
        student.setId(0);
        studentDao.save(student);
    }

    public Student findByEmail(String email) {
        return studentDao.findByEmailAddress(email);
    }

    public Iterable<CollegeStudent> getStudents(){
        return studentDao.findAll();
    }
}
