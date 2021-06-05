package org.hillel.hibernate.jpa.repository;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.io.Serializable;
import java.util.Collection;

public interface CommonJpaRepository<E extends Persistable<ID>, ID extends Serializable> {
    Collection<E> findByName(String name);

    @Query("select e from #{#entityName} e where e.active = true")
    Collection<E> findOnlyActive(String name);

    Collection<E> findByID(Long id);

    Collection<E> findAllByIds(Long[] id);

    @Query("select e from #{#entityName} e where e.id between :id_from and :id_to and e.name = :name")
    Collection<E> findBetween(@Param("name") String name,
                              @Param("id_from") Long idFrom,
                              @Param("id_to") Long idTo,
                              Pageable pageable);
    
}
