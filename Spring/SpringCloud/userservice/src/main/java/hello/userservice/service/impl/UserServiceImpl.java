package hello.userservice.service.impl;

import hello.userservice.domain.dto.ResponseOrder;
import hello.userservice.domain.dto.UserDto;
import hello.userservice.domain.entity.UserEntity;
import hello.userservice.repository.UserRepository;
import hello.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto){
        log.info("createUser method is called");
        log.info("userDto email: {}", userDto.getEmail());
        UserDto userDto1 = UserDto.builder()
                .userId(UUID.randomUUID().toString())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .encryptedPwd(userDto.getPassword())
                .build();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDto1, UserEntity.class);
        userEntity.setEncryptedPwd(bCryptPasswordEncoder.encode(userDto.getPassword()));

        userRepository.save(userEntity);

        return userDto1;
    }

    @Override
    public UserDto getUserByUserId(String userId){
        log.info("getUserByUserId method is called");
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null){
            throw new UsernameNotFoundException("User not found");
        }
        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        List<ResponseOrder> orderList = List.of(new ResponseOrder());

        List<UserDto> userDtoList = List.of(userDto);
        userDto.setOrders(orderList);
        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }
}
