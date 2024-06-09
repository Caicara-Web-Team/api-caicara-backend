package br.com.caicara.backend.model.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class RibeirinhoCreateDto {

    @NotBlank
    @Size(min = 3 ,max = 200)
    private String name;
    @NotBlank
    @Size(min = 11 ,max = 11)
    @CPF
    private String cpf;
    @NotBlank
    @Size(min = 14 ,max = 14)
    private String contact;
    @NotBlank
    @Size(min = 3 ,max = 200)
    private String address;
}
