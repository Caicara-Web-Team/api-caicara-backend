package br.com.caicara.backend.model.repositories.users;

import br.com.caicara.backend.model.entities.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByEmail(String email);

    @Query("select u.role from users u where u.email like :email")
    Users.Role findRoleByEmail(String email);
}
