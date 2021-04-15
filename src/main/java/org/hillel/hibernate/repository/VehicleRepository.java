package org.hillel.hibernate.repository;

import org.hillel.hibernate.entities.VehicleEntity;
import org.springframework.stereotype.Repository;

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
}
