package br.com.jjdev.APIREST.rest.dto;

import jakarta.validation.constraints.Email;

public record LoginDTO(

        @Email
        String email,

        String password
) {
}
