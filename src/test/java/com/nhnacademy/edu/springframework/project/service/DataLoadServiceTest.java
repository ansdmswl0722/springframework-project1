package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    Scores scores ;
    Students students;
    @BeforeEach
    void init() {
        scores = new CsvScores();
        students = new CsvStudents();
        CsvDataLoadService service = new CsvDataLoadService(scores,students);
        service.loadAndMerge();



    }

    @Test
    void testLoadAndMerge() {
        //Given 제대로 들어갔는가
        Collection<Student> actual = students.findAll();
        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(1,new Student(1,"A"));
        studentMap.put(2,new Student(2,"B"));
        studentMap.put(3,new Student(3,"A"));
        studentMap.put(4,new Student(4,"D"));
        studentMap.get(1).setScore(new Score(1,30));
        studentMap.get(2).setScore(new Score(2,80));
        studentMap.get(3).setScore(new Score(3,70));
        //When
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(studentMap.values());
    }

    @AfterEach
    void cleanup() {

    }
}