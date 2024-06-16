package ManyToMany.ServicesMTM;

import ManyToMany.Entity.CursoMTM;
import ManyToMany.RepositoryMTM.CourseRepositoryMTM;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceMTM {

    private final CourseRepositoryMTM courseRepository;

    public CourseServiceMTM(CourseRepositoryMTM courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CursoMTM saveCourse(CursoMTM course) {
        return courseRepository.save(course);
    }

    public List<CursoMTM> getAllCourses() {
        return courseRepository.findAll();
    }
}

