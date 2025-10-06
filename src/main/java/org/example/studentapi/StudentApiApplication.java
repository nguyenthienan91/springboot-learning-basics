package org.example.studentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }

    // Restfull API
    // Quản lý sinh viên

    // 1. Method (GET, POST: create new, PUT: update, DELETE: xoá )
    // 2. url

    // 1. Lấy danh sách tất cả sinh viên
    // => GET: /api/student

    // 1b. Lấy ra thông tin của 1 thằng sinh viên bằng id
    // => GET: /api/student/id

    // 2. Tạo ra 1 thằng sinh viên mới
    // => POST: /api/student

    // 3. Update thông tin của 1 thằng sinh viên
    // => PUT: /api/student/id

    // 4. Delete thông tin 1 thằng nào đó
    // => DELETE: /api/student/id
}
