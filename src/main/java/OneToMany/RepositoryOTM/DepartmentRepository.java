package OneToMany.RepositoryOTM;

import OneToMany.EntityOTM.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}