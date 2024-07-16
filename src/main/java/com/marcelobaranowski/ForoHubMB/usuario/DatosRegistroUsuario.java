package com.marcelobaranowski.ForoHubMB.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroUsuario(

        @NotBlank
        String nombre,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]{6,}$", message = "Debe ser alfanumerica con al menos 6 caracteres")
        String contrasena
) {
}
