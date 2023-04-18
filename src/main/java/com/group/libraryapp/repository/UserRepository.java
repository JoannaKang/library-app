package com.group.libraryapp.repository;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // sql 을 사용해 실제 DB 와의 통신을 담당한다
    public boolean isUserNotExist(long id) {
        // 업데이트 하려는 유저가 데이터베이스에 존재하는지 먼저 확인
        String readSql = "SELECT * FROM user WHERE id = ?";
        // 유저가 존재한다면 0이 리스트로 감싸져서 반환됨, 존재하지 않는다면 query 가 반환한 리스트가 비어있을 것
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateUserName(String name, long id) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public boolean isUserNotExist(String name) {
        // 삭제하려는 유저가 데이터베이스에 존재하는지 먼저 확인
        String readSql = "SELECT * FROM user WHERE name = ?";
        // 유저가 존재한다면 0이 리스트로 감싸져서 반환됨, 존재하지 않는다면 query 가 반환한 리스트가 비어있을 것
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void deleteUser(String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public void saveUser (String name, Integer age) {
        String sql = "INSERT INTO user(name, age) VALUES(?,?)";
        jdbcTemplate.update(sql, name, age);
    }

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
}
