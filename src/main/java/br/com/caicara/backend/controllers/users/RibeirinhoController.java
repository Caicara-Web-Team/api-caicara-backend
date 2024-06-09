package br.com.caicara.backend.controllers.users;

import br.com.caicara.backend.model.dto.users.RibeirinhoCreateDto;
import br.com.caicara.backend.model.dto.users.RibeirinhoResponseDto;
import br.com.caicara.backend.model.dto.users.RibeirinhoUpdateDto;
import br.com.caicara.backend.model.util.mapper.RibeirinhoMapper;
import br.com.caicara.backend.model.entities.users.Ribeirinho;
import br.com.caicara.backend.model.jwt.JwtUserDetails;
import br.com.caicara.backend.model.services.users.RibeirinhoService;
import br.com.caicara.backend.model.services.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/ribeirinho")
@RequiredArgsConstructor
public class RibeirinhoController {

    private final RibeirinhoService ribeirinhoService;
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('RIBEIRINHO')")
    public ResponseEntity<RibeirinhoResponseDto> createRibeirinho(@RequestBody @Valid RibeirinhoCreateDto dto, @AuthenticationPrincipal JwtUserDetails userDetails){
        Ribeirinho ribeirinho = RibeirinhoMapper.toRibeirinho(dto);
        ribeirinho.setUsers(userService.findUserById(userDetails.getId()));
        ribeirinhoService.createRibeirinho(ribeirinho);
        return ResponseEntity.status(HttpStatus.CREATED).body(RibeirinhoMapper.toDto(ribeirinho));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<RibeirinhoResponseDto>> getAllRibeirinhos(){
        List<Ribeirinho> ribs = ribeirinhoService.getAllRibeirinhos();
        return ResponseEntity.ok(RibeirinhoMapper.toListDto(ribs));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<RibeirinhoResponseDto> getRibeirinhoById(@PathVariable UUID id){
        Ribeirinho ribeirinho = ribeirinhoService.getRibeirinhoById(id);
        return ResponseEntity.ok(RibeirinhoMapper.toDto(ribeirinho));
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('RIBEIRINHO')")
    public ResponseEntity<Void> updateRibeirinho(@RequestBody @Valid RibeirinhoUpdateDto dto, @PathVariable UUID id){
        ribeirinhoService.updateRibeirinho(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhes")
    @PreAuthorize("hasRole('RIBEIRINHO')")
    public ResponseEntity<RibeirinhoResponseDto> getDetalhes(@AuthenticationPrincipal JwtUserDetails userDetails){
        Ribeirinho rib = ribeirinhoService.getUserById(userDetails.getId());
        return ResponseEntity.ok(RibeirinhoMapper.toDto(rib));
    }
}
