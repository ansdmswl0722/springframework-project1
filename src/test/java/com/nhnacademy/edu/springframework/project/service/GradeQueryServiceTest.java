package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeQueryServiceTest {

    Scores scores ;
    Students students;
    @BeforeEach
    void init() {
        scores = CsvScores.getInstance();
        scores.load();
        students = CsvStudents.getInstance();
        students.load();
        students.merge(scores.findAll());

    }

    @Test
    void testGetScoreByStudentName() {
        DefaultGradeQueryService service = new DefaultGradeQueryService();
        List<Score> actual = service.getScoreByStudentName("A");
        List<Score> scoreList = new ArrayList<>();
        scoreList.add(new Score(1,30));
        scoreList.add(new Score(3,70));
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(scoreList);
    }

    @Test
    void testGetScoreByStudentSeq() {
        DefaultGradeQueryService service = new DefaultGradeQueryService();
        Score actual = service.getScoreByStudentSeq(1);
        Score score = new Score(1,30);
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(score);

    }

    @AfterEach
    void cleanup() {

    }
}