package br.com.caicara.backend.model.util.mapper;

import br.com.caicara.backend.model.dto.users.RibeirinhoCreateDto;
import br.com.caicara.backend.model.dto.users.RibeirinhoResponseDto;
import br.com.caicara.backend.model.entities.users.Ribeirinho;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RibeirinhoMapper {

    public static RibeirinhoResponseDto toDto(Ribeirinho dto){
        return new ModelMapper().map(dto, RibeirinhoResponseDto.class);
    }
    public static Ribeirinho toRibeirinho (RibeirinhoCreateDto dto){
        return new ModelMapper().map(dto, Ribeirinho.class);
    }

    public static List<RibeirinhoResponseDto> toListDto(List<Ribeirinho> users){
        return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
