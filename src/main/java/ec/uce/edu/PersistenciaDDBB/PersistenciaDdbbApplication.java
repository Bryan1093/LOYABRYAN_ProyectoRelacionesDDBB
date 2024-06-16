package ec.uce.edu.PersistenciaDDBB;

import ManyToMany.Entity.CursoMTM;
import ManyToMany.Entity.EstudianteMTM;
import ManyToMany.RepositoryMTM.CourseRepositoryMTM;
import ManyToMany.RepositoryMTM.StudentRepositoryMTM;
import ManyToOne.EntityMTO.BossMTO;
import ManyToOne.EntityMTO.EmployeeMTO;
import ManyToOne.RepositoryMTO.BossMTORepository;
import ManyToOne.RepositoryMTO.EmployeeMTORepository;
import OneToMany.EntityOTM.Department;
import OneToMany.EntityOTM.Employee;
import OneToMany.RepositoryOTM.DepartmentRepository;
import OneToMany.RepositoryOTM.EmployeeRepository;
import OneToOne.Entity.IdentificationCard;
import OneToOne.Entity.Student;
import OneToOne.Repository.IdentificationCardRepository;
import OneToOne.Repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
@EntityScan(basePackages = {"OneToOne.Entity", "OneToMany.EntityOTM", "ManyToOne.EntityMTO", "ManyToMany.Entity"})
@EnableJpaRepositories(basePackages = {"OneToOne.Repository", "OneToMany.RepositoryOTM", "ManyToOne.RepositoryMTO", "ManyToMany.RepositoryMTM"})
@ComponentScan(basePackages = {"OneToOne", "OneToMany", "ManyToOne", "ManyToMany", "ec.uce.edu.PersistenciaDDBB"})
public class PersistenciaDdbbApplication implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IdentificationCardRepository identificationCardRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BossMTORepository bossRepository;

    @Autowired
    private EmployeeMTORepository employeeRepositoryMTO;

    @Autowired
    private CourseRepositoryMTM cursoRepository;

    @Autowired
    private StudentRepositoryMTM estudianteRepository;

    public static void main(String[] args) {
        SpringApplication.run(PersistenciaDdbbApplication.class, args);
    }

    @Override
    public void run(String... args) {
        mostrarMenu();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ejemplo OneToOne");
            System.out.println("2. Ejemplo OneToMany");
            System.out.println("3. Ejemplo ManyToOne");
            System.out.println("4. Ejemplo ManyToMany");
            System.out.println("5. Salir");

            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    ejemploOneToOne();
                    break;
                case 2:
                    ejemploOneToMany();
                    break;
                case 3:
                    ejemploManyToOne();
                    break;
                case 4:
                    ejemploManyToMany();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione otra opción.");
            }
        } while (opcion != 5);
    }

    public void ejemploOneToOne() {
        // EJEMPLO DE ONE TO ONE
        // Insertar un estudiante
        Student student = new Student();
        student.setName("John");

        IdentificationCard identificationCard = new IdentificationCard();
        identificationCard.setNumber("123456");

        student.setIdentificationCard(identificationCard);
        identificationCard.setStudent(student);

        studentRepository.save(student);

        // Consultar todos los estudiantes
        Iterable<Student> students = studentRepository.findAll();
        System.out.println("Estudiantes:");
        for (Student s : students) {
            System.out.println(s.getName());
        }

        // Consultar un estudiante por su ID
        Optional<Student> optionalStudent = studentRepository.findById(1L);
        if (optionalStudent.isPresent()) {
            Student foundStudent = optionalStudent.get();
            System.out.println("Estudiante encontrado: " + foundStudent.getName());
        } else {
            System.out.println("Estudiante no encontrado");
        }

        // Eliminar un estudiante
        studentRepository.delete(student);

        // Verificar que el estudiante ha sido eliminado
        Optional<Student> deletedStudent = studentRepository.findById(student.getId());
        if (deletedStudent.isPresent()) {
            System.out.println("El estudiante no fue eliminado");
        } else {
            System.out.println("El estudiante fue eliminado correctamente");
        }
    }

    //-----------------------------------------------------------------------------------------------

    public void ejemploOneToMany() {
        // EJEMPLO DE ONE TO MANY
        // Crear un departamento
        Department department = new Department();
        department.setName("IT");
        departmentRepository.save(department);

        // Crear empleados y asignarlos al departamento
        Employee employee1 = new Employee();
        employee1.setName("Alice");
        employee1.setDepartment(department);
        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Bob");
        employee2.setDepartment(department);
        employeeRepository.save(employee2);

        // Consultar el departamento y sus empleados
        Department savedDepartment = departmentRepository.findById(department.getId()).orElse(null);
        if (savedDepartment != null) {
            System.out.println("Departamento: " + savedDepartment.getName());
            System.out.println("Empleados:");
            for (Employee employee : savedDepartment.getEmployees()) {
                System.out.println(employee.getName());
            }
        } else {
            System.out.println("Departamento no encontrado");
        }
    }

    //-----------------------------------------------------------------------------------------------

    public void ejemploManyToOne() {
        //EJEMPLO DE MANY TO ONE
        // Crear un jefe
        BossMTO boss = new BossMTO();
        boss.setName("Alice");
        bossRepository.save(boss);

        BossMTO boss2 = new BossMTO();
        boss2.setName("Bryan");
        bossRepository.save(boss2);

        // Imprimir el ID de los jefes
        System.out.println("ID del primer jefe: " + boss.getId());
        System.out.println("ID del segundo jefe: " + boss2.getId());

        // Crear empleados y asignarles el mismo jefe
        EmployeeMTO employee1MTO = new EmployeeMTO();
        employee1MTO.setName("Bob");
        employee1MTO.setBoss(boss);
        employeeRepositoryMTO.save(employee1MTO);

        EmployeeMTO employee2MTO = new EmployeeMTO();
        employee2MTO.setName("Charlie");
        employee2MTO.setBoss(boss);
        employeeRepositoryMTO.save(employee2MTO);

        // Consultar el jefe y sus empleados
        BossMTO savedBoss = bossRepository.findById(boss.getId()).orElse(null);

        if (savedBoss != null) {
            System.out.println("Jefe: " + savedBoss.getName());
            System.out.println("Empleados:");
            for (EmployeeMTO employee : savedBoss.getEmployees()) {
                System.out.println(employee.getName());
            }
        } else {
            System.out.println("Jefe no encontrado");
        }

		// Llamar al método getEmployeesByBossId con un ID de jefe específico (por ejemplo, 1L)
		List<EmployeeMTO> employees = getEmployeesByBossId(1L);
		if (!employees.isEmpty()) {
			System.out.println("Empleados del jefe con ID 1:");
			for (EmployeeMTO employee : employees) {
				System.out.println(employee.getName());
			}
		} else {
			System.out.println("No se encontraron empleados para el jefe con ID 1");
		}

		// Llamar al método eliminarJefe con un ID de jefe específico (por ejemplo, 2L)
		eliminarJefe(2L);
		System.out.println("Jefe con ID 2 eliminado correctamente");
    }

    //-----------------------------------------------------------------------------------------------

    public void ejemploManyToMany() {
        //EJEMPLO DE MANYTOMANY
        // Crear un curso
        CursoMTM curso = new CursoMTM();
        curso.setNombre("Matemáticas");
        cursoRepository.save(curso);

        // Crear estudiantes
        EstudianteMTM estudiante1 = new EstudianteMTM();
        estudiante1.setNombre("Juan");
        estudianteRepository.save(estudiante1);

        EstudianteMTM estudiante2 = new EstudianteMTM();
        estudiante2.setNombre("María");
        estudianteRepository.save(estudiante2);

        // Inscribir estudiantes en el curso
        curso.getEstudiantes().add(estudiante1);
        curso.getEstudiantes().add(estudiante2);
        cursoRepository.save(curso);

        // Consultar el curso y sus estudiantes
        CursoMTM cursoGuardado = cursoRepository.findById(curso.getId()).orElse(null);
        if (cursoGuardado != null) {
            System.out.println("Curso: " + cursoGuardado.getNombre());
            System.out.println("Estudiantes:");
            for (EstudianteMTM estudiante : cursoGuardado.getEstudiantes()) {
                System.out.println(estudiante.getNombre());
            }
        } else {
            System.out.println("Curso no encontrado");
        }
    }

    @Transactional
    public List<EmployeeMTO> getEmployeesByBossId(Long bossId) {
        BossMTO boss = bossRepository.findById(bossId).orElse(null);
        if (boss != null) {
            // Initialize the collection
            boss.getEmployees().size();
            return boss.getEmployees();
        }
        return Collections.emptyList();
    }

	@Transactional
	public void eliminarJefe(Long jefeId) {
		Optional<BossMTO> optionalBoss = bossRepository.findById(jefeId);
		if (optionalBoss.isPresent()) {
			BossMTO boss = optionalBoss.get();
			List<EmployeeMTO> employees = employeeRepositoryMTO.findByBossId(jefeId);
			for (EmployeeMTO employee : employees) {
				employeeRepositoryMTO.delete(employee);
			}
			bossRepository.delete(boss);
		} else {
			throw new JefeNoEncontradoException("El jefe con ID " + jefeId + " no existe");
		}
	}

	public class JefeNoEncontradoException extends RuntimeException {
		public JefeNoEncontradoException(String message) {
			super(message);
		}
	}

}