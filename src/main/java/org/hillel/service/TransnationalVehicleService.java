package org.hillel.service;

import org.hillel.hibernate.entities.VehicleEntity;
import org.hillel.hibernate.jpa.repository.VehicleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransnationalVehicleService {

/*    @Autowired
    private VehicleRepository vehicleRepository;*/

    @Autowired
    private VehicleJpaRepository vehicleRepository;

    @Transactional()
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity){
        // return vehicleRepository.createOrUpdate(vehicleEntity);
        return vehicleRepository.save(vehicleEntity);
    }

    @Transactional
    public void remove(VehicleEntity vehicalEntity){
        //vehicleRepository.remove(vehicalEntity);
        vehicleRepository.delete(vehicalEntity);
    }

    @Transactional
    public Collection<VehicleEntity> findByIds(Long ... ids){
        //return vehicleRepository.findByIds(ids);
        return (Collection<VehicleEntity>) vehicleRepository.findAllById(Arrays.asList(ids));
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

    @Autowired
    private NewTransnationalVehicleService newTransnationalVehicleService;

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll(){
        return (Collection<VehicleEntity>) vehicleRepository.findAll();
    }

    @Transactional
    public Collection<VehicleEntity> findAllByName(String name){
        final Collection<VehicleEntity> byName = vehicleRepository.findByName(name);
        /*final VehicleEntity next = byName.iterator().next();
        next.setName(String.valueOf(System.currentTimeMillis()));
        System.out.println("Save vehicle with id =" + next.getId() + "and new value "+ next.getName());
        newTransnationalVehicleService.createOrUpdate(next);*/

        return byName;
    }
}
