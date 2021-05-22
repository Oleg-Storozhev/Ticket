package org.hillel.service;

import org.hillel.hibernate.entities.StopEntity;
import org.hillel.hibernate.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class TransactionalStopService {

    @Autowired
    private StopRepository stopRepository;

    @Transactional
    public StopEntity createOrUpdate(StopEntity stopEntity){
        return stopRepository.createOrUpdate(stopEntity);
    }

    public void remove(StopEntity stopEntity){
        stopRepository.remove(stopEntity);
    }

    public void removeByID(Long ID) {
        stopRepository.removeById(ID);
    }

    @Transactional
    public Collection<StopEntity> findAll(){
        return stopRepository.findAll();
    }
    @Transactional
    public Collection<StopEntity> findAllAsNatie() {
        return stopRepository.findAllAsNative();
    }
    @Transactional
    public Collection<StopEntity> findAllVehiclesAsNamed() {
        return stopRepository.findAllAsNamed();
    }
    @Transactional
    public Collection<StopEntity> findAllVehiclesAsCriteria() {
        return stopRepository.findAllAsCriteria();
    }
    @Transactional
    public Collection<StopEntity> findAllVehiclesAsStoredProcedure() {
        return stopRepository.findAllAsStoredProcedure();
    }

    public Collection<StopEntity> getAllStopsSortedByID(int start, int max) {
        return stopRepository.findAllSortedByID(start, max);
    }

    public Collection<StopEntity> getAllStopsSortedByActive(int start, int max) {
        return stopRepository.findAllSortedByActive(start, max);
    }
}
