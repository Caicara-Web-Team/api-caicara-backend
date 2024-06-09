package br.com.caicara.backend.model.util.mapper;

import br.com.caicara.backend.model.dto.products.ProductCreateDto;
import br.com.caicara.backend.model.dto.products.ProductResponseDto;
import br.com.caicara.backend.model.entities.products.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static Product toProduct(ProductCreateDto dto){
        return new ModelMapper().map(dto, Product.class);
    }

    public static ProductResponseDto toDto(Product product){
        return new ModelMapper().map(product,ProductResponseDto.class);
    }

    public static List<ProductResponseDto> toListDto(List<Product> products){
        return products.stream().map(product -> toDto(product)).collect(Collectors.toList());
    }
}
