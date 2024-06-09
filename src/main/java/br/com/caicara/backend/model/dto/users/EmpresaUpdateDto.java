package br.com.caicara.backend.model.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmpresaUpdateDto {


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
