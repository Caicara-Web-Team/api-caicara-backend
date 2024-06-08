package br.com.caicara.backend.model.dto.users;

import lombok.*;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserResponseDto {

    private UUID id;
    private String email;
    private String role;
}
