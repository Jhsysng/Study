package hello.userservice.controller;

import com.netflix.discovery.converters.Auto;
import hello.userservice.domain.dto.RequestUser;
import hello.userservice.domain.dto.ResponseUser;
import hello.userservice.domain.dto.UserDto;
import hello.userservice.domain.entity.UserEntity;
import hello.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/")
@Slf4j
public class UsersController {

    private Environment env;
    private final UserService userService;

    @Autowired
    public UsersController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }
    @GetMapping("/health_check")
    public String status() {
        return String.format(
                "It's Working in User Service on PORT %s, " +
                        "port(local.server.port)=%s, " +
                        "port(server.port)=%s, " +
                        "token secret=%s, " +
                        "token expiration time=%s",
                Objects.requireNonNullElse(env.getProperty("local.server.port"), "Unknown"),
                Objects.requireNonNullElse(env.getProperty("local.server.port"), "Unknown"),
                Objects.requireNonNullElse(env.getProperty("server.port"), "Unknown"),
                Objects.requireNonNullElse(env.getProperty("token.secret"), "Not Set"),
                Objects.requireNonNullElse(env.getProperty("token.expiration_time"), "Not Set")
        );
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody RequestUser user){
        log.info("Request user data {}, {}", user.getEmail(), user.getName());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        UserDto userDto1 = userService.createUser(user.toDto());

        ResponseUser responseUser = modelMapper.map(userDto1, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId){
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers(){
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>(List.of(new ResponseUser()));
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseUser.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/users/login")
    public String login(@RequestBody RequestUser user){
        return "login";
    }

}
