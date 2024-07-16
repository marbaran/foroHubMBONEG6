package com.marcelobaranowski.ForoHubMB.usuario;

public record DatosRespuestaUsuario(Long id, String nombre, String email, String contrasena) {

    public DatosRespuestaUsuario(UsuarioDB usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
    }
}
