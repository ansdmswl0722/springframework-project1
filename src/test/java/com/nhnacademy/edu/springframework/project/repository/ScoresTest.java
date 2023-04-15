package com.nhnacademy.edu.springframework.project.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ScoresTest {

    @Test
    void testLoad() {
        Scores csvScores = CsvScores.getInstance();
        csvScores.load();

        List<Score> scoreList = new ArrayList<>();
        scoreList.add(new Score(1,30));
        scoreList.add(new Score(2,80));
        scoreList.add(new Score(3,70));
        int actual = csvScores.findAll().size();
        Assertions.assertThat(actual).isEqualTo(scoreList.size());
    }

    @Test
    void testFindAll() {
        Scores csvScores = CsvScores.getInstance();
        csvScores.load();
        List<Score> scoreList = new ArrayList<>();
        scoreList.add(new Score(1,30));
        scoreList.add(new Score(2,80));
        scoreList.add(new Score(3,70));
        List<Score> actual = csvScores.findAll();
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(scoreList);

    }
}