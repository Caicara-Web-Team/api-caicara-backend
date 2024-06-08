package br.com.caicara.backend.model.util.mapper;

import br.com.caicara.backend.model.dto.users.UserCreateDto;
import br.com.caicara.backend.model.dto.users.UserResponseDto;
import br.com.caicara.backend.model.entities.users.Users;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static Users toUser(UserCreateDto createDto) {
        return new ModelMapper().map(createDto, Users.class);
    }

    public static UserResponseDto toDto(Users users) {
        return new ModelMapper().map(users, UserResponseDto.class);
    }

    public static List<UserResponseDto> toListDto(List<Users> users) {
        return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }

}
