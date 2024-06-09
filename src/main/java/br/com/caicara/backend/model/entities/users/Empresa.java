package br.com.caicara.backend.model.entities.users;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity()
@Table(name = "user_empresa")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Column(name = "cnpj", nullable = false,unique = true, length = 14)
    private String cnpj;
    @Column(name = "contact", nullable = false, length = 14)
    private String contact;
    @Column(name = "address", nullable = false, length = 200)
    private String address;
    @OneToOne
    @JoinColumn(name = "id_users", nullable = false)
    private  Users users;
}
