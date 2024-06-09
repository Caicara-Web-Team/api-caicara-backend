package br.com.caicara.backend.model.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RibeirinhoUpdateDto {

    @NotBlank
    @Size(min = 3 ,max = 200)
    private String name;
    @NotBlank
    @Size(min = 14 ,max = 14)
    private String contact;
    @NotBlank
    @Size(min = 3 ,max = 200)
    private String address;
}
