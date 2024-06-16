package ManyToMany.RepositoryMTM;

import ManyToMany.Entity.CursoMTM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositoryMTM extends JpaRepository<CursoMTM, Long> {
}

