package br.com.caicara.backend.model.services.users;

import br.com.caicara.backend.model.entities.users.Users;
import br.com.caicara.backend.model.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Users createUser(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    @Transactional(readOnly = true)
    public Users findUserById(UUID id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Id= '%s' não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<Users> findAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(UUID id){
        userRepository.deleteById(id);
    }

    @Transactional
    public Users updatePassword(UUID id, String currentPassword, String newPassword, String confirmPassword){
        if (!newPassword.equals(confirmPassword))
            throw new RuntimeException("Nova senha não confere com confirmação de senha");

        Users users = findUserById(id);
        if (!passwordEncoder.matches(currentPassword, users.getPassword()))
            throw new RuntimeException("Sua senha não confere");

        users.setPassword(passwordEncoder.encode(newPassword));
        return users;
    }

    @Transactional(readOnly = true)
    public Users getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
    }

    @Transactional(readOnly = true)
    public Users.Role getRoleByEmail(String email) {
        return userRepository.findRoleByEmail(email);
    }
}
