package com.marcelobaranowski.ForoHubMB.publicacion;

import com.marcelobaranowski.ForoHubMB.curso.Curso;

import java.time.LocalDateTime;

public record DatosRespuestaPublicacion(Long id, String titulo, String mensaje,
                                        LocalDateTime fecha, Curso curso) {
    public DatosRespuestaPublicacion(PublicacionDB publicacion) {
        this (publicacion.getId(),
                publicacion.getTitulo(),
                publicacion.getMensaje(),
                publicacion.getFecha(),
                publicacion.getCurso()
        );
    }
}
