package br.com.jjdev.APIREST.service;

import br.com.jjdev.APIREST.domain.user.Roles;
import br.com.jjdev.APIREST.domain.user.User;
import br.com.jjdev.APIREST.repository.UserRepository;
import br.com.jjdev.APIREST.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isOwnerOfUser(Authentication authentication, Long userId) {
        String username = authentication.name();
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() && user.get().getUsername().equals(username);
    }

    public String createUser(UserDTO userDTO) throws Exception {
        this.userEmailAlreadyExists(userDTO.email());

        User createdUser = new User();
        BeanUtils.copyProperties(userDTO, createdUser);
        createdUser.setRole(Roles.COSTUMER);
        createdUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.password()));
        userRepository.save(createdUser);
        return "User created successfully";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        Optional<User> queriedUser = this.userRepository.findById(id);

        if (queriedUser.isEmpty()) {
            throw new Exception("Not Found");
        } else return queriedUser.get();
    }

    public User editUserById(Long id, UserDTO userDTO) throws Exception {
        User editedUser = this.findUserById(id);

        if (userDTO.name() != null) {
            editedUser.setName(userDTO.name());
        }

        if (userDTO.email() != null) {

            if (this.userEmailAlreadyExists(userDTO.email())) {
                throw new Exception("Email already exists");
            }
            editedUser.setEmail(userDTO.email());
        }

        if (userDTO.password() != null) {
            editedUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.password()));
        }
        this.userRepository.save(editedUser);
        return editedUser;
    }

    private Boolean userEmailAlreadyExists(String email) {
        return this.userRepository.findAllByEmail(email).isPresent();
    }

    public String deleteUserById(Long id) throws Exception {
        this.userRepository.deleteById(this.findUserById(id).getId());

        return "User deleted successfully";
    }
}
