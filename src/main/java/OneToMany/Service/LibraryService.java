package OneToMany.Service;

import OneToMany.Entity.Library;
import OneToMany.Repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    public Library save(Library library) {
        return libraryRepository.save(library);
    }

    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    public Optional<Library> findById(Long id) {
        return libraryRepository.findById(id);
    }

    public void deleteById(Long id) {
        libraryRepository.deleteById(id);
    }
}