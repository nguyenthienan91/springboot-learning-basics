package org.example.studentapi.controller;

import org.example.studentapi.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController     //đánh dấu spring boot sẽ biết đây là lớp chứa API
public class StudentController {

    List<Student> students = new ArrayList<>();

    //1. Lấy danh sách tất cả các sinh viên => GET
    @GetMapping("/api/student")
    public ResponseEntity get() {return ResponseEntity.ok(students);}


    //2. Tạo ra một sinh viên mới => POST
    @PostMapping("/api/student")
    public ResponseEntity create(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.ok(student);
    }

}
