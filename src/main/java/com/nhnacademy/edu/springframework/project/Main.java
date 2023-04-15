package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class Main {

    // TODO 9 - 성공적으로 실행되어야 합니다.
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project")){

        DataLoadService dataLoadService = context.getBean("csvDataLoadService", DataLoadService.class);
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = context.getBean("studentService", DefaultStudentService.class);
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

        DefaultGradeQueryService service = context.getBean("gradeQueryService", DefaultGradeQueryService.class);
        System.out.println(service.getScoreByStudentName("A"));
        System.out.println(service.getScoreByStudentSeq(1));
        }
    }
}
