package org.hillel.hibernate.jpa.repository;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface CommonJpaRepository<E extends Persistable<ID>, ID extends Serializable> {
    List<E> findByName(String name);

    @Query("select e from #{#entityName} e where e.active = true")
    List<E> findOnlyActive(String name);
}
