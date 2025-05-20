package ru.smak.service;

import io.ebean.DB;
import ru.smak.model.Grade;

public class GradeService {
    public void addGrade(Grade grade) {
        grade.save();
    }

    public void updateGrade(Long id, int score) {
        Grade grade = DB.find(Grade.class, id);
        if (grade != null) {
            grade.setScore(score);
            grade.update();
        }
    }

    public void deleteGrade(Long id) {
        Grade grade = DB.find(Grade.class, id);
        if (grade != null) {
            grade.delete();
        }
    }
}