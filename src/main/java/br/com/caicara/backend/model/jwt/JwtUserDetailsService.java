package br.com.caicara.backend.model.jwt;

import br.com.caicara.backend.model.entities.users.Users;
import br.com.caicara.backend.model.services.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userService.getByEmail(email);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated(String email){
        Users.Role role = userService.getRoleByEmail(email);
        return JwtUtils.createToken(email, role.name().substring("ROLE_".length()));
    }
}
