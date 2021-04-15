package org.hillel.hibernate.repository;

import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.hibernate.entities.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Objects;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity,Long>{

    protected JourneyRepository(Class<JourneyEntity> entityClass){
        super(JourneyEntity.class);
    }

    @Override
    public JourneyEntity createOrUpdate(JourneyEntity journeyEntity){
        VehicleEntity vehvehicleEntity = journeyEntity.getVehicle();
        if(Objects.nonNull(journeyEntity.getVehicle())){
            if(!entityManager.contains(vehvehicleEntity)){
                journeyEntity.setVehicle(entityManager.merge(vehvehicleEntity));
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

    public EntityManager getEntitymanager(){
        return entityManager;
    }
}
