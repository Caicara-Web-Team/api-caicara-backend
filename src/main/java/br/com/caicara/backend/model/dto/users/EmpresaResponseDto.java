package br.com.caicara.backend.model.dto.users;

import br.com.caicara.backend.model.entities.users.Users;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmpresaResponseDto {

    private String name;
    private String cnpj;
    private String contact;
    private String address;
    private String role = Users.Role.ROLE_EMPRESA.name();
}
