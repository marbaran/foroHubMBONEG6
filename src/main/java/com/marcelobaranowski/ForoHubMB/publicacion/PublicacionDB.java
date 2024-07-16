package com.marcelobaranowski.ForoHubMB.publicacion;

import com.marcelobaranowski.ForoHubMB.curso.Curso;
import com.marcelobaranowski.ForoHubMB.usuario.UsuarioDB;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "publicaciones")
@Entity(name = "Publicacion")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PublicacionDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private UsuarioDB autor;
    @Enumerated(EnumType.STRING)
    private Curso curso;

    public PublicacionDB (DatosRegistroPublicacion datosRegistroPublicacion) {
        this.titulo = datosRegistroPublicacion.titulo();
        this.mensaje = datosRegistroPublicacion.mensaje();
        this.fecha = datosRegistroPublicacion.fecha();
        this.status = true;
        this.autor = new UsuarioDB(datosRegistroPublicacion.autor());
        this.curso = datosRegistroPublicacion.curso();
    }

    public void actualizarDatosPublicacion(DatosActualizarPublicacion datosActualizarPublicacion) {
        if (datosActualizarPublicacion.titulo() != null) {
            this.titulo = datosActualizarPublicacion.titulo();
        }
        if (datosActualizarPublicacion.mensaje() != null) {
            this.mensaje = datosActualizarPublicacion.mensaje();
        }
        if (datosActualizarPublicacion.curso() != null) {
            this.curso = datosActualizarPublicacion.curso();
        }
    }
}

