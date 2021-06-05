package org.hillel.hibernate.jpa.repository;

import org.hillel.hibernate.entities.JourneyEntity;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.Collection;

public interface JourneyJpaRepository extends CommonJpaRepository<JourneyEntity, Long>, CrudRepository<JourneyEntity, Long> {
    Collection<JourneyEntity> findFirstByStationFrom(String stationFrom);
}
