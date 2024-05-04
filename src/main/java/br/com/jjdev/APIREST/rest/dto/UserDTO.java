package br.com.jjdev.APIREST.rest.dto;

import jakarta.validation.constraints.Email;

public record UserDTO(
        String name,

        @Email
        String email,

        String password) {
}
