package org.hillel.hibernate.repository;

import org.hillel.hibernate.entities.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StopRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final StopEntity stopEntity){
        entityManager.persist(stopEntity);
        return stopEntity.getId();
    }
}
