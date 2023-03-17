package com.luv2code.springmvc.repositories;

import com.luv2code.springmvc.models.Grade;
import com.luv2code.springmvc.models.MathGrade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MathGradesDao extends CrudRepository<MathGrade,Integer> {
    List<Grade> findGradeByStudentId(int id);

    void deleteByStudentId(int studentId);
}
