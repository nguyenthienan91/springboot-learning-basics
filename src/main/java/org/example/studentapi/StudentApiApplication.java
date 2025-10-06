package org.example.studentapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Student API", version = "1.0", description = "Information"))
@SecurityScheme(name = "api", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
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
