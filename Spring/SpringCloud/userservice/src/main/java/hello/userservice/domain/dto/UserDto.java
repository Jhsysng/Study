package hello.userservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String userId;
    private LocalDate createdAt;
    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
