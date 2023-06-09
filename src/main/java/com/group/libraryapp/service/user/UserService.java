package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.UserJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UserServiceV1 {
    private UserJdbcRepository userRepository;

    public UserServiceV1(UserJdbcRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void saveUser(UserCreateRequest request) {
        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request) {
        // 반환된 리스트가 비어있다면 예외처리
        if (userRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        userRepository.deleteUser(name);

        // 반환된 리스트가 비어있다면 예외처리
        if (userRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }


    }
}
