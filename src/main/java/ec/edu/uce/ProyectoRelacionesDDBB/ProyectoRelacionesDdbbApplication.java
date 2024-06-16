package ec.edu.uce.ProyectoRelacionesDDBB;

import ManyToMany.Entity.Course;
import ManyToMany.Entity.Student;
import ManyToMany.Service.CourseService;
import ManyToMany.Service.StudentService;
import ManyToOne.Entity.Author;
import ManyToOne.Service.AuthorService;
import ManyToOne.Entity.Book;
import OneToMany.Entity.Library;
import OneToMany.Service.BookService;
import OneToMany.Service.LibraryService;
import OneToOne.Entity.Person;
import OneToOne.Service.PassportService;
import OneToOne.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"OneToOne.Service", "OneToOne.Entity"})
@EnableJpaRepositories(basePackages = {"OneToOne.Repository", "OneToMany.Repository", "ManyToOne.Repository", "ManyToMany.Repository"})
public class ProyectoRelacionesDdbbApplication implements CommandLineRunner{

	@Autowired
	private PersonService personService;

	@Autowired
	private PassportService passportService;

	@Autowired
	private LibraryService libraryService;

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoRelacionesDdbbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Select an example to view:");
			System.out.println("1. OneToOne");
			System.out.println("2. OneToMany");
			System.out.println("3. ManyToOne");
			System.out.println("4. ManyToMany");
			System.out.println("0. Exit");

			choice = scanner.nextInt();
			switch (choice) {
				case 1:
					// OneToOne Example
					List<Person> persons = personService.findAll();
					for (Person person : persons) {
						System.out.println(person.getName() + " - " + person.getPassport().getNumber());
					}
					break;

				case 2:
					// OneToMany Example
					List<Library> libraries = libraryService.findAll();
					for (Library library : libraries) {
						System.out.println("Library: " + library.getName());
						for (OneToMany.Entity.Book book : library.getBooks()) {
							System.out.println("- Book: " + book.getTitle());
						}
					}
					break;
				case 3:
					// ManyToOne Example
					List<Author> authors = authorService.findAll();
					for (Author author : authors) {
						System.out.println(author.getName() + " - Books:");
						for (Book book : author.getBooks()) {
							System.out.println("-- " + book.getTitle());
						}
					}
					break;
				case 4:
					// ManyToMany Example
					List<Student> students = studentService.findAll();
					for (Student student : students) {
						System.out.println(student.getName() + " - Courses:");
						for (Course course : student.getCourses()) {
							System.out.println("-- " + course.getTitle());
						}
					}
					break;
				case 0:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
			}
		} while (choice != 0);

		scanner.close();
	}
}