package org.hillel.hibernate.repository;

import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E, ID> {
    E createOrUpdate(E entity);

    Optional<E> findByID(ID id);

    void removeById(ID id);

    void remove(E entity);

    Collection<E> findByIds(ID ... ids);

    Collection<E> findAll();
}
