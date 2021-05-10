package org.hillel.hibernate.jpa.repository;

import org.hillel.hibernate.entities.JourneyEntity;
import org.springframework.data.repository.CrudRepository;

public interface JourneyJpaRepository extends CommonJpaRepository<JourneyEntity, Long>, CrudRepository<JourneyEntity, Long> {

}
