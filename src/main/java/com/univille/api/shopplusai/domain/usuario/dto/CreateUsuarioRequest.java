package com.univille.api.shopplusai.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUsuarioRequest(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email)
{
}
