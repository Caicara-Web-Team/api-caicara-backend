package br.com.caicara.backend.controllers.users;

import br.com.caicara.backend.exception.ErrorMessage;
import br.com.caicara.backend.model.dto.users.UserLoginDto;
import br.com.caicara.backend.model.jwt.JwtToken;
import br.com.caicara.backend.model.jwt.JwtUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody @Valid UserLoginDto dto, HttpServletRequest request){
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            JwtToken token = jwtUserDetailsService.getTokenAuthenticated(dto.getEmail());
            return ResponseEntity.ok(token);
        }
        catch (AuthenticationException e){
            log.warn("Credenciais incorretas {}", dto.getEmail());
        }
        return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais inválidas"));
    }
}
