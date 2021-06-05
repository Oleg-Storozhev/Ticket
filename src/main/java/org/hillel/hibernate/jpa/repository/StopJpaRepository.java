package org.hillel.hibernate.jpa.repository;

import org.hillel.hibernate.entities.StopEntity;
import org.springframework.data.repository.CrudRepository;

public interface StopJpaRepository extends CommonJpaRepository<StopEntity, Long>, CrudRepository<StopEntity, Long> {

}
