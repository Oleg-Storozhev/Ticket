package org.hillel.hibernate.repository;

import org.hillel.hibernate.entities.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JourneyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final JourneyEntity journeyEntity){
        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }

    public Optional<JourneyEntity> findById(Long id){
        return Optional.ofNullable(entityManager.find(JourneyEntity.class, id));
    }
}
