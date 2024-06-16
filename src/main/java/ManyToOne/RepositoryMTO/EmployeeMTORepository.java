package ManyToOne.RepositoryMTO;

import ManyToOne.EntityMTO.EmployeeMTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeMTORepository extends JpaRepository<EmployeeMTO, Long> {
    List<EmployeeMTO> findByBossId(Long bossId);
}
