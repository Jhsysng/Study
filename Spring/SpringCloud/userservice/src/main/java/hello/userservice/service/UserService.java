package hello.userservice.service;


import hello.userservice.domain.dto.UserDto;
import hello.userservice.domain.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity > getUserByAll();
    UserDto getUserDetailsByEmail(String userName);
}

