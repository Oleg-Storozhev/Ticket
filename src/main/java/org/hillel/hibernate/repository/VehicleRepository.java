package org.hillel.hibernate.repository;

import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.hibernate.entities.JourneyEntity_;
import org.hillel.hibernate.entities.VehicleEntity;
import org.hillel.hibernate.entities.VehicleEntity_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.Collection;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {

    protected VehicleRepository(){
        super(VehicleEntity.class);
    }

    @Override
    public void remove(VehicleEntity entity){
        entity = findByID(entity.getId()).get();
        entity.removedAllJourneys();
        super.remove(entity);
    }

/*    @Override
    public Collection <VehicleEntity> findByName(String name){
        return entityManager.createQuery("from VehicleEntity e where e.name ");
    }*/
@Override
public Collection<VehicleEntity> findByName(String name) {
/*        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(VehicleEntity.class);
        final Root<VehicleEntity> from = criteriaQuery.from(VehicleEntity.class);
        final Join<Object, Object> journeys = from.join(VehicleEntity_.JOURNEYS, JoinType.LEFT);
        final Predicate byJourneyName = criteriaBuilder.equal(journeys.get(JourneyEntity_.STATION_FROM), criteriaBuilder.parameter(String.class, "stationFromParam"));
        journeys.on(byJourneyName);
        final Predicate byName = criteriaBuilder.equal(from.get(VehicleEntity_.NAME), criteriaBuilder.parameter(String.class, "nameParam"));
        final Predicate active = criteriaBuilder.equal(from.get(VehicleEntity_.ACTIVE), criteriaBuilder.parameter(Boolean.class, "activeParam"));

        return entityManager.createQuery(criteriaQuery.select(from).
            where(byName, active))
            .setParameter("nameParam", name)
            .setParameter("activeParam", true)
            .setParameter("stationFromParam", "from 1")
            .getResultList();
        */
    return entityManager
            .createQuery("select v from VehicleEntity v left join v.journeys js on js.vehicle.id = v.id")
            .setFirstResult(1)
            .setMaxResults(3)
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

}
