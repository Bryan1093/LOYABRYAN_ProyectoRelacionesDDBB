package OneToOne.Service;

import OneToOne.Entity.Passport;
import OneToOne.Repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassportService {
    @Autowired
    private PassportRepository passportRepository;

    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }

    public List<Passport> findAll() {
        return passportRepository.findAll();
    }

    public Optional<Passport> findById(Long id) {
        return passportRepository.findById(id);
    }

    public void deleteById(Long id) {
        passportRepository.deleteById(id);
    }
}