package org.hillel.hibernate.repository;

import org.hillel.hibernate.entities.StopEntity;
import org.springframework.stereotype.Repository;

@Repository
public class StopRepository extends CommonRepository<StopEntity, Long> {
    protected StopRepository(Class<StopEntity> entityClass){
        super(StopEntity.class);
    }
}
