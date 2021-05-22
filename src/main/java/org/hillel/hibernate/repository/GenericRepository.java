package org.hillel.hibernate.repository;

import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E, ID>{
    E createOrUpdate(E entity);

    Optional<E> findByID(ID id);

    void removeById(ID id);

    void remove(E entity);

    Collection<E> findByIds(ID ... ids);

    Collection<E> findByName(String name);

    Collection<E> findAll();

    Collection<E> findAllAsNative();

    Collection<E> findAllAsCriteria();

    Collection<E> findAllAsStoredProcedure();

    Collection<E> findAllAsNamed();
}
