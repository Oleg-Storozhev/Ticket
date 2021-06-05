package org.hillel.hibernate.jpa.repository;

import org.hillel.hibernate.entities.VehicleEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long>, CrudRepository<VehicleEntity, Long> {
    Collection<VehicleEntity> findAllByNameOrderByName(String name, PageRequest pageable);
}
