package org.hillel.hibernate.jpa.repository;

import org.hillel.hibernate.entities.VehicleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Collection;

public interface VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long>, CrudRepository<VehicleEntity, Long> {

    @Query("select v from VehicleEntity v where v.id between :id_from and :id_to and v.name = :name")
    Collection<VehicleEntity> findBetween(@Param("name") String name,
                                          @Param("id_from") Long idFrom,
                                          @Param("id_to") Long idTo,
                                          Pageable pageable);
}
