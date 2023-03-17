package com.luv2code.springmvc.repositories;

import com.luv2code.springmvc.models.Grade;
import com.luv2code.springmvc.models.HistoryGrade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryGradesDao extends CrudRepository<HistoryGrade,Integer> {
    List<Grade> findGradeByStudentId(int id);

    void deleteByStudentId(int studentId);
}
