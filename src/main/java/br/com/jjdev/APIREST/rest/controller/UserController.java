package br.com.jjdev.APIREST.rest.controller;


import br.com.jjdev.APIREST.domain.user.User;
import br.com.jjdev.APIREST.rest.dto.UserDTO;
import br.com.jjdev.APIREST.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(this.userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(this.userService.editUserById(id, userDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.userService.deleteUserById(id), HttpStatus.GONE);
    }
}
