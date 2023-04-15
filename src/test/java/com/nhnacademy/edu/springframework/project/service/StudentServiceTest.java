package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class StudentServiceTest {
    Scores scores ;
    Students students;
    @BeforeEach
    void init() {
        scores = new CsvScores();
        scores.load();
        students =new CsvStudents();
        students.load();
        students.merge(scores.findAll());

    }

    @Test
    void getPassedStudents() {
        DefaultStudentService service = new DefaultStudentService(students);
        Collection<Student> actual = service.getPassedStudents();

        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(2,new Student(2,"B"));
        studentMap.put(3,new Student(3,"A"));
        studentMap.get(2).setScore(new Score(2,80));
        studentMap.get(3).setScore(new Score(3,70));

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(studentMap.values());
    }

    @Test
    void getStudentsOrderByScore() {
        DefaultStudentService service = new DefaultStudentService(students);
        Collection<Student> actual = service.getStudentsOrderByScore();

        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(2,new Student(2,"B"));
        studentMap.put(3,new Student(3,"A"));
        studentMap.put(1,new Student(1,"A"));
        studentMap.put(4,new Student(4,"D"));
        studentMap.get(2).setScore(new Score(2,80));
        studentMap.get(3).setScore(new Score(3,70));
        studentMap.get(1).setScore(new Score(1,30));

        Collection<Student> expect = studentMap.values().stream()
                .filter(s -> Objects.nonNull(s.getScore()))
                .sorted(Comparator.comparing((Student student) -> student.getScore().getScore()).reversed())
                .collect(Collectors.toList());

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expect);


    }
}