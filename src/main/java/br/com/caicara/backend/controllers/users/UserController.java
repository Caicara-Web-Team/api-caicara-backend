package br.com.caicara.backend.controllers.users;

import br.com.caicara.backend.model.dto.users.UserCreateDto;
import br.com.caicara.backend.model.dto.users.UserResponseDto;
import br.com.caicara.backend.model.dto.users.UserUpdatePasswordDto;
import br.com.caicara.backend.model.util.mapper.UserMapper;
import br.com.caicara.backend.model.entities.users.Users;
import br.com.caicara.backend.model.services.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreateDto createDto){
        Users users = userService.createUser(UserMapper.toUser(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(users));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        List<Users> users = userService.findAllUsers();
        return ResponseEntity.ok(UserMapper.toListDto(users));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADM', 'RIBEIRINHO','EMPRESA') AND #id ==authentication.principal.id")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id){
        Users users = userService.findUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(users));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADM', 'RIBEIRINHO','EMPRESA') AND #id ==authentication.principal.id")
    public ResponseEntity<Void> deleteUserByid(@PathVariable UUID id){
        userService.deleteUserById(id);
        return  ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADM', 'RIBEIRINHO','EMPRESA') AND #id ==authentication.principal.id")
    public ResponseEntity<Void> updatePassword(@PathVariable UUID id, @Valid @RequestBody UserUpdatePasswordDto dto){
        userService.updatePassword(id, dto.getCurrentPassword(), dto.getNewPassword(), dto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

}
