package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;


class StudentsTest {

    @Test
    void testLoad() {
        Students students = CsvStudents.getInstance();
        students.load();

        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(1,new Student(1,"A"));
        studentMap.put(2,new Student(2,"B"));
        studentMap.put(3,new Student(3,"A"));
        studentMap.put(4,new Student(4,"D"));
        int actual = students.findAll().size();
        Assertions.assertThat(actual).isEqualTo(studentMap.size());

    }

    @Test
    void testFindAll() {
        Students students = CsvStudents.getInstance();
        students.load();
        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(1,new Student(1,"A"));
        studentMap.put(2,new Student(2,"B"));
        studentMap.put(3,new Student(3,"A"));
        studentMap.put(4,new Student(4,"D"));
        Collection<Student> actual = students.findAll();
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(studentMap.values());

    }

    @Test
    void testMerge() {
        Students students = CsvStudents.getInstance();
        students.load();
        Scores csvScores = CsvScores.getInstance();
        csvScores.load();
        List<Score> scores = csvScores.findAll();
        students.merge(scores);
        Collection<Student> actual = students.findAll();

        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(1,new Student(1,"A"));
        studentMap.put(2,new Student(2,"B"));
        studentMap.put(3,new Student(3,"A"));
        studentMap.put(4,new Student(4,"D"));
        studentMap.get(1).setScore(new Score(1,30));
        studentMap.get(2).setScore(new Score(2,80));
        studentMap.get(3).setScore(new Score(3,70));

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(studentMap.values());


    }
}