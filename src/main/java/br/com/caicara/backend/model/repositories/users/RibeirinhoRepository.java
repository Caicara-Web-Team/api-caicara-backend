package br.com.caicara.backend.model.repositories.users;

import br.com.caicara.backend.model.entities.users.Ribeirinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository

public interface RibeirinhoRepository extends JpaRepository<Ribeirinho, UUID>{

    Ribeirinho findByUsersId(UUID id);

    Optional<Ribeirinho> findByCpf(String cpf);

    //Ribeirinho findByUsername(String username);
}
