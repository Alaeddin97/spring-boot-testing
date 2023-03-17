package com.luv2code.springmvc.repositories;

import com.luv2code.springmvc.models.Grade;
import com.luv2code.springmvc.models.ScienceGrade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScienceGradesDao extends CrudRepository<ScienceGrade,Integer> {
    List<Grade> findGradeByStudentId(int i);

    void deleteByStudentId(int studentId);
}
