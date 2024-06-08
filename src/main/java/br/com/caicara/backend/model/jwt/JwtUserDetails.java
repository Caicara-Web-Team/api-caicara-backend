package br.com.caicara.backend.model.jwt;

import br.com.caicara.backend.model.entities.users.Users;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.UUID;

public class JwtUserDetails extends User {

    private Users user;

    public JwtUserDetails(Users user){
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public UUID getId(){
        return this.user.getId();
    }

    public String getRole(){
        return this.user.getRole().name();
    }
}
