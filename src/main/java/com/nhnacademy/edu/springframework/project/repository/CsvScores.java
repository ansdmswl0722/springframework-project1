package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository("csvScores")
public class CsvScores implements Scores {
    private List<Score> scoreList;


    @Override
    public void load() {
        try (InputStream inputStream = new ClassPathResource("data/score.csv").getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line = "";
            scoreList = new ArrayList<>();
            while ((line=reader.readLine()) != null) {
                String[] array = line.split(",");
                int seq = Integer.parseInt(array[0]);
                int score = Integer.parseInt(array[1]);
                Score scoreObject = new Score(seq,score);
                scoreList.add(scoreObject);
            }
            System.out.println(scoreList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
