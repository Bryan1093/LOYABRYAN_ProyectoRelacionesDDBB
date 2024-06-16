package OneToMany.RepositoryOTM;

import OneToMany.EntityOTM.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
