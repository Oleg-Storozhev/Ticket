package org.hillel.service;

import org.hillel.hibernate.entities.StopEntity;
import org.hillel.hibernate.jpa.repository.StopJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class TransactionalStopService {

    @Autowired
    private StopJpaRepository stopRepository;

    @Transactional
    public StopEntity createOrUpdate(StopEntity stopEntity){
        return stopRepository.save(stopEntity);
    }

    public Collection<StopEntity> findAll() {
        return (Collection<StopEntity>) stopRepository.findAll();
    }

    public Collection<StopEntity> findAllSortedByID(PageRequest of) {
        return stopRepository.findAllSortedByID(of);
    }

    public Collection<StopEntity> findAllSortedByActive(PageRequest of) {
        return stopRepository.findAllSortedByActive(of);
    }
}
