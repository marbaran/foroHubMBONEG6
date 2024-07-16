package com.marcelobaranowski.ForoHubMB.publicacion;

import com.marcelobaranowski.ForoHubMB.curso.Curso;
import com.marcelobaranowski.ForoHubMB.usuario.DatosRegistroUsuario;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarPublicacion(

        @NotNull
        Long id,

        String titulo,

        String mensaje,

        DatosRegistroUsuario autor,

        Curso curso
) {

}
