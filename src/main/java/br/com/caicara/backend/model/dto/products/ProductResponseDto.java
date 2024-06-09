package br.com.caicara.backend.model.dto.products;

import br.com.caicara.backend.model.dto.users.RibeirinhoResponseDto;
import br.com.caicara.backend.model.dto.users.RibeirinhoResponsePublicDto;
import br.com.caicara.backend.model.entities.products.Product;
import br.com.caicara.backend.model.entities.users.Ribeirinho;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Product.Category category;
    private RibeirinhoResponsePublicDto ribeirinho;

}
