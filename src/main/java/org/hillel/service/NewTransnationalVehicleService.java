package org.hillel.service;

import org.hillel.hibernate.entities.VehicleEntity;
import org.hillel.hibernate.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Service
public class NewTransnationalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity){
        return vehicleRepository.createOrUpdate(vehicleEntity);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void remove(VehicleEntity vehicalEntity){
        vehicleRepository.remove(vehicalEntity);
    }

    public Collection<VehicleEntity> findByIds(Long ... ids){
        return vehicleRepository.findByIds(ids);
    }

    @Transactional(readOnly = true)
    public Optional<VehicleEntity> findById(Long id, boolean withDep){
        final Optional<VehicleEntity> byID = vehicleRepository.findByID(id);
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
        return vehicleRepository.findAll();
    }
}
