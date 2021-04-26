package org.hillel.hibernate.repository;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.annotations.Table;
import org.hillel.hibernate.entities.AbstractModifyEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;


public abstract class CommonRepository<E extends AbstractModifyEntity<ID>, ID extends Serializable> implements GenericRepository<E, ID> {

    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<E> entityClass;

    protected CommonRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E createOrUpdate(E entity) {
        Assert.notNull(entity, "Entity must be a set");
        if (Objects.isNull(entity.getId())) {
            entityManager.persist(entity);
        } else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Optional<E> findByID(ID id) {
        if (Objects.isNull(id)) return Optional.empty();
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @SneakyThrows
    @Override
    public void removeById(ID id) {
        final E reference = entityManager.getReference(entityClass, id);
        entityManager.remove(reference);
    }

    @Override
    public void remove(E entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            removeById(entity.getId());
        }
    }

    @Override
    public final Collection<E> findByIds(ID... ids) {
        return entityManager.unwrap(Session.class).byMultipleIds(entityClass).multiLoad(ids);
    }

    @Override
    public final Collection<E> findByName(String name) {
        final  CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(entityClass);
        final Root <E> from = criteriaQuery.from(entityClass);
        final Join<Object, Object> journeys = from.join("journeys", JoinType.LEFT);
        final Predicate byJourneyName = criteriaBuilder.equal(journeys.get("StationFrom"), criteriaBuilder.parameter(String.class, "stationFromParam"));
        journeys.on(byJourneyName);
        final Predicate byName = criteriaBuilder.equal(from.get("name"), criteriaBuilder.parameter(String.class, "nameParam"));
        final Predicate active = criteriaBuilder.equal(from.get("active"), criteriaBuilder.parameter(Boolean.class, "activeParam"));

        return entityManager.createQuery(criteriaQuery.select(from).
                where(byName, active))
                .setParameter("nameParam", name)
                .setParameter("activeParam", true)
                .setParameter("stationFromParam", "from 1")
                .getResultList();
        /*return entityManager.createNativeQuery("select e.* from " + entityClass.getAnnotation(Table.class).name() + " e " + "e.name = ?", entityClass).setParameter(1, name)
                .setParameter(1, name)
                .getResultList();*/
/*        return entityManager.createQuery("from " + entityClass.getName()
                + " e where e.name = :entityName and e.active = :activeParam", entityClass)
                .setParameter("entityName", name)
                .setParameter("activeParam", true)
                .getResultList();*/
/*        return entityManager.createNativeQuery("select e.* from " + entityClass.getAnnotation(Table.class).name() + " e " + "where e.name = :entityName and e.active = :activeParam", entityClass)
                .setParameter("entityName", name)
                .setParameter("activeParam", "yes")
                .getResultList();*/

    }

    @Override
    public Collection<E> findAll() {
        //return entityManager.createNativeQuery("select * from " + entityClass.getAnnotation(Table.class).name(), entityClass).getResultList();
        //return entityManager.createQuery("from "+ entityClass.getSimpleName(), entityClass).getResultList();

/*        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        final Root<E> from = query.from(entityClass);

        return entityManager.createQuery(query.select(from)).getResultList();*/
        return entityManager.createStoredProcedureQuery("find_all", entityClass)
                .registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(2,entityClass.getAnnotation(Table.class))
                .getResultList();
    }

}