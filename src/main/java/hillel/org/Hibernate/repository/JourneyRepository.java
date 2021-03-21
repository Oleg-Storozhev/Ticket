package hillel.org.Hibernate.repository;

import hillel.org.Hibernate.persistance.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JourneyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final JourneyEntity journeyEntity){
        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }

}
