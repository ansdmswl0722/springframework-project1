package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("csvDataLoadService")
public class CsvDataLoadService implements DataLoadService {

    private final Scores scores;
    private final Students students;

    @Autowired
    public CsvDataLoadService(Scores scores, Students students) {
        this.scores = scores;
        this.students = students;
    }

    @Override
    public void loadAndMerge() {
        scores.load();
        students.load();
        students.merge(scores.findAll());
    }
}
