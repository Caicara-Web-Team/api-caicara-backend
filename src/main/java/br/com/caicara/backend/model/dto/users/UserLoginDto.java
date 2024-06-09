package br.com.caicara.backend.model.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, max = 8)
    private String password;
}
