package hello.userservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String userId;
    private LocalDate createdAt;
    private String encryptedPwd;
}
