package br.com.caicara.backend.model.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class EmpresaCreateDto {


    @NotBlank
    @Size(min = 3 ,max = 200)
    private String name;
    @NotBlank
    @Size(min = 14 ,max = 14)
    @CNPJ
    private String cnpj;
    @NotBlank
    @Size(min = 14 ,max = 14)
    private String contact;
    @NotBlank
    @Size(min = 3 ,max = 200)
    private String address;
}
