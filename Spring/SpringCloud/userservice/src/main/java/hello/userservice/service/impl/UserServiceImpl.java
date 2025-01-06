package hello.userservice.service.impl;

import hello.userservice.domain.dto.UserDto;
import hello.userservice.repository.UserRepository;
import hello.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto){
        UserDto userDto1 = UserDto.builder()
                .userId(UUID.randomUUID().toString())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .encryptedPwd(userDto.getPassword())
                .build();

        //ModelMapper modelMapper = new ModelMapper();

        return userDto1;
    }


}
