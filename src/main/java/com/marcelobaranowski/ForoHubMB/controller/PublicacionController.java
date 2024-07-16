package com.marcelobaranowski.ForoHubMB.controller;

import com.marcelobaranowski.ForoHubMB.publicacion.*;
import com.marcelobaranowski.ForoHubMB.usuario.DatosActualizarUsuario;
import com.marcelobaranowski.ForoHubMB.usuario.DatosRespuestaUsuario;
import com.marcelobaranowski.ForoHubMB.usuario.UsuarioDB;
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
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPublicacion> registrarPublicacion(@RequestBody @Valid DatosRegistroPublicacion datosRegistroPublicacion, UriComponentsBuilder uriComponentsBuilder) {

        PublicacionDB publicacion = publicacionRepository.save(new PublicacionDB(datosRegistroPublicacion));
        DatosRespuestaPublicacion datosRespuestaPublicacion = new DatosRespuestaPublicacion(
                publicacion.getId(), publicacion.getTitulo(), publicacion.getMensaje(),
                publicacion.getFecha(), publicacion.getCurso());
        URI url = uriComponentsBuilder.path("/publicaciones/{id}").buildAndExpand(publicacion.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPublicacion);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaPublicacion>> listadoPublicaciones(
            @PageableDefault(size = 3) Pageable pagination) {
        return ResponseEntity.ok(publicacionRepository.findAll(pagination)
                .map(DatosRespuestaPublicacion::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPublicacion> listarPublicacionById(@PathVariable Long id) {

        PublicacionDB publicacion = publicacionRepository.getReferenceById(id);
        var datosPublicacion = new DatosRespuestaPublicacion(publicacion);

        return ResponseEntity.ok(datosPublicacion);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaPublicacion> modificarPublicacionb(@RequestBody DatosActualizarPublicacion datosActualizarPublicacion, @PathVariable Long id) {

        PublicacionDB publicacion = publicacionRepository.getReferenceById(datosActualizarPublicacion.id());

        publicacion.actualizarDatosPublicacion(datosActualizarPublicacion);
        return ResponseEntity.ok(new DatosRespuestaPublicacion(publicacion));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPublicacion(@PathVariable Long id) {

        Optional<PublicacionDB> publicacionPresent = publicacionRepository.findById(id);
        publicacionRepository.deleteById(publicacionPresent.get().getId());
        return ResponseEntity.noContent().build();
    }
}

