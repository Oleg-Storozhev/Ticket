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
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {
    private final Class<VehicleEntity> entityClass;
    protected VehicleRepository(Class<VehicleEntity> entityClass){
        super(VehicleEntity.class);
        this.entityClass = entityClass;
    }

    @Override
    public void remove(VehicleEntity entity){
        entity = findByID(entity.getId()).get();
        entity.removedAllJourneys();
        super.remove(entity);
    }

    @Override
    public Collection<VehicleEntity> findAll() {
        return entityManager.createQuery("select v from VehicleEntity v").getResultList();
    }

    @Override
    public Collection<VehicleEntity> findAllAsNative(){
        return entityManager.createNativeQuery("select * from vehicleentity").getResultList();
    }

    @Override
    public Collection<VehicleEntity> findAllAsNamed() {
        return entityManager.createNamedQuery("findAllVehicles").getResultList();
    }

    @Override
    public Collection<VehicleEntity> findAllAsCriteria() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<VehicleEntity> query = criteriaBuilder.createQuery(VehicleEntity.class);
        final Root<VehicleEntity> from = query.from(VehicleEntity.class);
        return entityManager.createQuery(query.select(from)).getResultList();
    }

    @Override
    public Collection<VehicleEntity> findAllAsStoredProcedure() {
        return entityManager.createStoredProcedureQuery("findAllVehicles", VehicleEntity.class)
                .registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(2,VehicleEntity.class.getAnnotation(Table.class))
                .getResultList();
    }

    @Override
    public Collection<VehicleEntity> findByName(String name) {
    return entityManager
            .createQuery("select v from VehicleEntity v left join v.journeys js on js.vehicle.id = v.id")
            .setFirstResult(1)
            .setMaxResults(3)
            .getResultList();
    }

    public Collection<VehicleEntity> findAllSortedByID(int start, int max) {
        return entityManager
                .createQuery("select v from VehicleEntity v order by v.id")
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();
    }
    public Collection<VehicleEntity> findAllSortedByName(int start, int max){
        return entityManager
                .createQuery("select v from VehicleEntity v order by v.name")
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();
    }

    public Collection<VehicleEntity> findAllSortedByActive(int start, int max) {
        return entityManager
                .createQuery("select v from VehicleEntity v order by v.active")
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();
    }
}
