package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.core.io.FileSystemResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class CsvStudents implements Students {

    private String studentCsvUrl = "/Users/muneunji/Downloads/springframework-project1/src/main/resources/data/student.csv";
    private Map<Integer,Student> studentMap;
    private CsvStudents(){}

    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    private static class SingleInstancesHolder {
        private static final CsvStudents INSTANCE = new CsvStudents();
    }
    public static Students getInstance() {
        return SingleInstancesHolder.INSTANCE;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        try(InputStream inputStream = new FileSystemResource(studentCsvUrl).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ){
            String line ="";
            studentMap = new HashMap<>();
            while ((line=reader.readLine()) != null) {
                String[] array = line.split(",");
                int seq = Integer.parseInt(array[0]);
                Student student = new Student(seq,array[1]);
                studentMap.put(seq,student);
            }
            System.out.println(studentMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentMap.values();
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
       scores.stream()
               .forEach(score -> {
                   Student student = studentMap.get(score.getStudentSeq());
                   student.setScore(score);
               });
    }
}
