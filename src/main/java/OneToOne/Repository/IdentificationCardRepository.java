package OneToOne.Repository;

import OneToOne.Entity.IdentificationCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationCardRepository extends JpaRepository<IdentificationCard, Long> {
}
