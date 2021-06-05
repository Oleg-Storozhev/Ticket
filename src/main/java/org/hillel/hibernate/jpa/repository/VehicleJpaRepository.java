package org.hillel.hibernate.jpa.repository;

import org.hillel.hibernate.entities.VehicleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;

public interface VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long>, CrudRepository<VehicleEntity, Long> {
}
