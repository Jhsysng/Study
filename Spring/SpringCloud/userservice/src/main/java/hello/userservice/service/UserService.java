package hello.userservice.service;


import hello.userservice.domain.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}

