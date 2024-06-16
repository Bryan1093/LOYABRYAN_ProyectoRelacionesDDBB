package ManyToMany.ServicesMTM;

import ManyToMany.Entity.EstudianteMTM;
import ManyToMany.RepositoryMTM.StudentRepositoryMTM;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceMTM {

    private final StudentRepositoryMTM studentRepository;

    public StudentServiceMTM(StudentRepositoryMTM studentRepository) {
        this.studentRepository = studentRepository;
    }

    public EstudianteMTM saveStudent(EstudianteMTM student) {
        return studentRepository.save(student);
    }

    public List<EstudianteMTM> getAllStudents() {
        return studentRepository.findAll();
    }

}
