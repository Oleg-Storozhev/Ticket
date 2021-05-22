package org.hillel.service;

import org.hillel.hibernate.entities.VehicleEntity;
import org.hillel.hibernate.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransnationalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity){
        return vehicleRepository.createOrUpdate(vehicleEntity);
    }

    @Transactional
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

    @Autowired
    private NewTransnationalVehicleService newTransnationalVehicleService;

    @Transactional
    public Collection<VehicleEntity> findAllByName(String name){
        final Collection<VehicleEntity> byName = vehicleRepository.findByName(name);
        final VehicleEntity next = byName.iterator().next();
        next.setName(String.valueOf(System.currentTimeMillis()));
        System.out.println("Save vehicle with id =" + next.getId() + "and new value "+ next.getName());
        newTransnationalVehicleService.createOrUpdate(next);
        return byName;
    }

    public void removeByID(Long id) {
        vehicleRepository.removeById(id);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll(){
        return vehicleRepository.findAll();
    }
    @Transactional
    public Collection<VehicleEntity> findAllAsNatie() {
       return vehicleRepository.findAllAsNative();
    }
    @Transactional
    public Collection<VehicleEntity> findAllVehiclesAsNamed() {
        return vehicleRepository.findAllAsNamed();
    }
    @Transactional
    public Collection<VehicleEntity> findAllVehiclesAsCriteria() {
        return vehicleRepository.findAllAsCriteria();
    }
    @Transactional
    public Collection<VehicleEntity> findAllVehiclesAsStoredProcedure() {
        return vehicleRepository.findAllAsStoredProcedure();
    }

    @Transactional
    public Collection<VehicleEntity> getAllVehiclesSortedByID(int start, int max) {
        return vehicleRepository.findAllSortedByID(start, max);
    }
    @Transactional
    public Collection<VehicleEntity> getAllVehiclesSortedByName(int start, int max) {
        return vehicleRepository.findAllSortedByName(start, max);
    }
    @Transactional
    public Collection<VehicleEntity> getAllVehiclesSortedByActive(int start, int max) {
        return vehicleRepository.findAllSortedByActive(start, max);
    }
}
