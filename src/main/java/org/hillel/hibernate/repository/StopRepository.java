package org.hillel.hibernate.repository;

import org.hibernate.annotations.Table;
import org.hillel.hibernate.entities.StopEntity;
import org.hillel.hibernate.entities.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
public class StopRepository extends CommonRepository<StopEntity, Long> {
    private final Class<StopEntity> entityClass;
    protected StopRepository(Class<StopEntity> entityClass){
        super(StopEntity.class);
        this.entityClass = entityClass;
    }
    @Override
    public Collection<StopEntity> findAll() {
        return entityManager.createQuery("from "+ StopEntity.class.getSimpleName(), StopEntity.class).getResultList();
    }

    @Override
    public Collection<StopEntity> findAllAsNative(){
        return entityManager.createNativeQuery("select * from " + StopEntity.class.getAnnotation(Table.class).getClass().getName(), StopEntity.class).getResultList();
    }

    @Override
    public Collection<StopEntity> findAllAsNamed() {
        return entityManager.createNamedQuery("findAllStops").getResultList();
    }

    @Override
    public Collection<StopEntity> findAllAsCriteria() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<StopEntity> query = criteriaBuilder.createQuery(StopEntity.class);
        final Root<StopEntity> from = query.from(StopEntity.class);
        return entityManager.createQuery(query.select(from)).getResultList();
    }

    @Override
    public Collection<StopEntity> findAllAsStoredProcedure() {
        return entityManager.createStoredProcedureQuery("findAllStops", StopEntity.class)
                .registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(2,StopEntity.class.getAnnotation(Table.class))
                .getResultList();
    }


}
