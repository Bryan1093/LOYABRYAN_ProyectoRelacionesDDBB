package ManyToOne.ServiceMTO;

import ManyToOne.EntityMTO.BossMTO;
import ManyToOne.RepositoryMTO.BossMTORepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BossMTOService {

    @Autowired
    private BossMTORepository repository;

    @Transactional
    public List<BossMTO> getAllBosses() {
        List<BossMTO> bosses = repository.findAll();
        bosses.forEach(boss -> boss.getEmployees().size());
        return bosses;
    }

    public BossMTO saveBoss(BossMTO boss) {
        return repository.save(boss);
    }

    public void deleteBoss(Long id) {
        repository.deleteById(id);
    }
}
