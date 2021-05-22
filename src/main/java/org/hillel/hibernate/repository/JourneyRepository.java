package org.hillel.hibernate.repository;

import org.hibernate.annotations.Table;
import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.hibernate.entities.StopEntity;
import org.hillel.hibernate.entities.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Objects;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity,Long>{
    private final Class<JourneyEntity> entityClass;
    protected JourneyRepository(Class<JourneyEntity> entityClass){
        super(JourneyEntity.class);
        this.entityClass = entityClass;
    }

    @Override
    public JourneyEntity createOrUpdate(JourneyEntity journeyEntity){
        VehicleEntity vehhicleEntity = journeyEntity.getVehicle();
        if(Objects.nonNull(journeyEntity.getVehicle())){
            if(!entityManager.contains(vehhicleEntity)){
                journeyEntity.setVehicle(entityManager.merge(vehhicleEntity));
            }
        }
        return super.createOrUpdate(journeyEntity);
    }

    @Override
    public void removeById(Long id){
        final JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setId(id);
        super.remove(journeyEntity);
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

/*    @Override
    public Collection<JourneyEntity> findAll() {
        return entityManager.createQuery("select v from JourneyEntity v").getResultList();
    }

    @Override
    public Collection<JourneyEntity> findAllAsNative(){
        return entityManager.createNativeQuery("select * from journey").getResultList();
    }

    @Override
    public Collection<JourneyEntity> findAllAsNamed() {
        return entityManager.createNamedQuery("findAllJourneys").getResultList();
    }

    @Override
    public Collection<JourneyEntity> findAllAsCriteria() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<JourneyEntity> query = criteriaBuilder.createQuery(JourneyEntity.class);
        final Root<JourneyEntity> from = query.from(JourneyEntity.class);
        return entityManager.createQuery(query.select(from)).getResultList();
    }

    @Override
    public Collection<JourneyEntity> findAllAsStoredProcedure() {
        return entityManager.createStoredProcedureQuery("findAllJourneys", JourneyEntity.class)
                .registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(2,JourneyEntity.class.getAnnotation(Table.class))
                .getResultList();
    }*/


/*    public Collection<JourneyEntity> findAllSortedByID(int start, int max) {
        return entityManager
                .createQuery("select v from JourneyEntity v order by v.id")
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();
    }*/
}
