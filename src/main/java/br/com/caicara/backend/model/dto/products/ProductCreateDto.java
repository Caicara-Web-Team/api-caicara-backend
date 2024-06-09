package br.com.caicara.backend.model.dto.products;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductCreateDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotBlank
    @Pattern(regexp = "PEIXES|CRUSTACEOS|MOLUSCOS")
    @Size(max = 15)
    private String category;
}
