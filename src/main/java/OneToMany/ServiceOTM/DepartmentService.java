package OneToMany.ServiceOTM;

import OneToMany.EntityOTM.Department;
import OneToMany.EntityOTM.Employee;
import OneToMany.RepositoryOTM.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));

        return department.getEmployees();
    }
}