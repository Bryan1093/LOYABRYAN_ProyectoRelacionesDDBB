package ManyToMany.Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CursoMTM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER) // Carga la colección estudiantes de manera inmediata
    @JoinTable(
            name = "inscripcion_mtm",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id"))
    private Set<EstudianteMTM> estudiantes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<EstudianteMTM> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<EstudianteMTM> estudiantes) {
        this.estudiantes = estudiantes;
    }
}