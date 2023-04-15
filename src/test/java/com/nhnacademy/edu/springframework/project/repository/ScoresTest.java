package com.nhnacademy.edu.springframework.project.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class ScoresTest {
    Scores csvScores ;

    @BeforeEach
    void init() {
        csvScores = new CsvScores();
        csvScores.load();

    }

    @Test
    void testLoad() {
        List<Score> scoreList = new ArrayList<>();
        scoreList.add(new Score(1,30));
        scoreList.add(new Score(2,80));
        scoreList.add(new Score(3,70));
        int actual = csvScores.findAll().size();
        Assertions.assertThat(actual).isEqualTo(scoreList.size());
    }

    @Test
    void testFindAll() {
        List<Score> scoreList = new ArrayList<>();
        scoreList.add(new Score(1,30));
        scoreList.add(new Score(2,80));
        scoreList.add(new Score(3,70));
        List<Score> actual = csvScores.findAll();
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(scoreList);

    }
}