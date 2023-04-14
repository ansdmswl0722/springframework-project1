package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.core.io.FileSystemResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {
    private String scoreCsvUrl = "/Users/muneunji/Downloads/springframework-project1/src/main/resources/data/score.csv";
    private List<Score> scoreList;

    private CsvScores(){}

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    private static class SingleInstancesHolder {
        private static final CsvScores INSTANCE = new CsvScores();
    }
    public static Scores getInstance() {
        return SingleInstancesHolder.INSTANCE;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try(InputStream inputStream = new FileSystemResource(scoreCsvUrl).getInputStream();
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
