package org.hillel.hibernate.jpa.repository;

import org.hillel.hibernate.entities.JourneyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface JourneyJpaRepository extends CommonJpaRepository<JourneyEntity, Long>, CrudRepository<JourneyEntity, Long> {
    Collection<JourneyEntity> findAllByStationFromOrderByStationFrom(String stationFrom);
    Collection<JourneyEntity> findFirstByStationFrom(String stationFrom);
}
