package com.marcelobaranowski.ForoHubMB.controller;

import com.marcelobaranowski.ForoHubMB.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {

        UsuarioDB usuario = usuarioRepository.save(new UsuarioDB(datosRegistroUsuario));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(
                usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> listadoUsuarios(
            @PageableDefault(size = 4) Pageable pagination) {
        return ResponseEntity.ok(usuarioRepository.findAll(pagination)
                .map(DatosRespuestaUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoUsuario> listarUsuarioById(@PathVariable Long id) {

        UsuarioDB usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosListadoUsuario(usuario);

        return ResponseEntity.ok(datosUsuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> modificarUsuario(@RequestBody DatosActualizarUsuario datosActualizarUsuario, @PathVariable Long id) {

        UsuarioDB usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());

        usuario.actualizarDatosUsuario(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {

        Optional<UsuarioDB> usuarioPresent = usuarioRepository.findById(id);
        usuarioRepository.deleteById(usuarioPresent.get().getId());
        return ResponseEntity.noContent().build();
    }
}

