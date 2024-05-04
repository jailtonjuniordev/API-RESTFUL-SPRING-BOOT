package br.com.jjdev.APIREST.repository;

import br.com.jjdev.APIREST.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findAllByEmail(String email);

    UserDetails findByEmail(String email);
}
