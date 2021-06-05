package org.hillel.service;

import org.hillel.hibernate.entities.VehicleEntity;
import org.hillel.hibernate.jpa.repository.VehicleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class NewTransnationalVehicleService {

    @Autowired
    private VehicleJpaRepository vehicleRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity){
        return vehicleRepository.save(vehicleEntity);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void remove(VehicleEntity vehicalEntity){
        vehicleRepository.delete(vehicalEntity);
    }

    public Collection<VehicleEntity> findByIds(Long ... ids){
        return vehicleRepository.findAllByIds(Arrays.asList(ids));
    }

    @Transactional(readOnly = true)
    public Optional<VehicleEntity> findById(Long id, boolean withDep){
        final Optional<VehicleEntity> byID = vehicleRepository.findById(id);
        if(byID.isPresent()){
            return byID;
        }
        if(!withDep){
            return byID;
        }
        byID.get().getJourneys().size();
        return byID;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll(){
        return (Collection<VehicleEntity>) vehicleRepository.findAll();
    }
}
