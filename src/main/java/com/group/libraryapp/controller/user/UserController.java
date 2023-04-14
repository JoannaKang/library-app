package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO user(name, age) VALUES(?,?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        // jdbcTemplate.query: sql 의 실행결과를 우리가 원하는 UserResponse 의 형태로 변환해준다
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // SQL 에서 실제 값을 받아오는 부분
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        // 업데이트 하려는 유저가 데이터베이스에 존재하는지 먼저 확인
        String readSql = "SELECT * FROM user WHERE id = ?";
        // 유저가 존재한다면 0이 리스트로 감싸져서 반환됨, 존재하지 않는다면 query 가 반환한 리스트가 비어있을 것
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        // 반환된 리스트가 비어있다면 예외처리
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }

    @DeleteMapping("/user")
    public  void deleteUser(@RequestParam String name) {
        // 삭제하려는 유저가 데이터베이스에 존재하는지 먼저 확인
        String readSql = "SELECT * FROM user WHERE name = ?";
        // 유저가 존재한다면 0이 리스트로 감싸져서 반환됨, 존재하지 않는다면 query 가 반환한 리스트가 비어있을 것
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        // 반환된 리스트가 비어있다면 예외처리
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }
}
