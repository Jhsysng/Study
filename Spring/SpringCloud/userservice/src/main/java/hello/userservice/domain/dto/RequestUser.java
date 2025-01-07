package hello.userservice.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RequestUser {
    @NotNull
    @Size(min = 3, message = "Name cannot be less than 3 characters")
    @Email
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equal or greater than 8 characters")
    private String password;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be equal or greater than 2 characters")
    private String name;

    public UserDto toDto() {
        return UserDto.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

}
