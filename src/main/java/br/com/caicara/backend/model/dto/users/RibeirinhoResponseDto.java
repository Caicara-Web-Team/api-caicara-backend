package br.com.caicara.backend.model.dto.users;

import br.com.caicara.backend.model.entities.products.Product;
import br.com.caicara.backend.model.entities.users.Users;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RibeirinhoResponseDto {

    private UUID id;
    private String name;
    private String cpf;
    private String contact;
    private String address;
    private String role = Users.Role.ROLE_RIBEIRINHO.name();
    private List<Product> products;
}
