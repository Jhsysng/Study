package hello.userservice.service;


import hello.userservice.domain.dto.UserDto;
import hello.userservice.domain.entity.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity > getUserByAll();
}

