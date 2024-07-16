package com.marcelobaranowski.ForoHubMB.usuario;

public record DatosListadoUsuario(Long id, String nombre, String email, String contrasena) {

    public DatosListadoUsuario(UsuarioDB usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
    }
}
