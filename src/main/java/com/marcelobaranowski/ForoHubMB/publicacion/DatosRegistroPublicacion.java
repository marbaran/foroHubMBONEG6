package com.marcelobaranowski.ForoHubMB.publicacion;

import com.marcelobaranowski.ForoHubMB.curso.Curso;
import com.marcelobaranowski.ForoHubMB.usuario.DatosRegistroUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroPublicacion(

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotNull
        LocalDateTime fecha,

        Boolean status,

        //@NotNull
        @Valid DatosRegistroUsuario autor,

        @NotNull
        Curso curso
) {

}
