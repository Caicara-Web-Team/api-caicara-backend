package br.com.caicara.backend.model.dto.users;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RibeirinhoResponsePublicDto {

    private String name;
    private String contact;
    private String address;
}
