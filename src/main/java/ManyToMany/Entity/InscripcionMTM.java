package ManyToMany.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inscripcion")
public class InscripcionMTM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private EstudianteMTM estudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoMTM curso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
