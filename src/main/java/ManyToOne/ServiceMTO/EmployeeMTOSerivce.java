package ManyToOne.ServiceMTO;

import ManyToOne.EntityMTO.EmployeeMTO;
import ManyToOne.RepositoryMTO.EmployeeMTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeMTOSerivce {

    @Autowired
    private EmployeeMTORepository repository;

    public List<EmployeeMTO> getAllEmployees() {
        return repository.findAll();
    }

    public EmployeeMTO saveEmployee(EmployeeMTO employee) {
        return repository.save(employee);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}