package ManyToOne.RepositoryMTO;

import ManyToOne.EntityMTO.BossMTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BossMTORepository extends JpaRepository<BossMTO, Long> {
}