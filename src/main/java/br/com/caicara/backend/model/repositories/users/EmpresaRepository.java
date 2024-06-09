package br.com.caicara.backend.model.repositories.users;

import br.com.caicara.backend.model.entities.users.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

    Empresa findByUsersId(UUID id);
}
