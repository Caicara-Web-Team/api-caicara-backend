package br.com.caicara.backend.model.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserUpdatePasswordDto {

    @NotBlank
    @Size(min = 8, max = 8)
    private String currentPassword;
    @NotBlank
    @Size(min = 8, max = 8)
    private String newPassword;
    @NotBlank
    @Size(min = 8, max = 8)
    private String confirmPassword;

}
