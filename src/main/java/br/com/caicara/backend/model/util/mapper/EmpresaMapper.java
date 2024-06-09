package br.com.caicara.backend.model.util.mapper;

import br.com.caicara.backend.model.dto.users.EmpresaCreateDto;
import br.com.caicara.backend.model.dto.users.EmpresaResponseDto;
import br.com.caicara.backend.model.entities.users.Empresa;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class EmpresaMapper {

    public static EmpresaResponseDto toDto(Empresa emp) {
        return new ModelMapper().map(emp, EmpresaResponseDto.class);
    }

    public static Empresa toEmpresa(EmpresaCreateDto dto){
        return new ModelMapper().map(dto, Empresa.class);
    }

    public static List<EmpresaResponseDto> toListEmpresa(List<Empresa> list){
        return list.stream().map(emp -> toDto(emp)).collect(Collectors.toList());
    }

}
