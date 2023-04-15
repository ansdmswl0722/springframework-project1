package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.service.*;

import java.util.Collection;

public class Main {

    // TODO 9 - 성공적으로 실행되어야 합니다.
    public static void main(String[] args) {
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = new DefaultStudentService();
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

        DefaultGradeQueryService service = new DefaultGradeQueryService();
//        service.getScoreByStudentName("A");
        System.out.println(service.getScoreByStudentName("A"));
        System.out.println(service.getScoreByStudentSeq(1));
    }
}
