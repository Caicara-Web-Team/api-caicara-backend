package br.com.caicara.backend.model.entities.users;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Column(name = "role", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Role role ;

    public enum Role {
        ROLE_RIBEIRINHO,
        ROLE_EMPRESA,
        ROLE_ADM;
    }
}
