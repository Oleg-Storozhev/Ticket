package org.hillel.service;

import org.hillel.hibernate.entities.StopEntity;
import org.hillel.hibernate.jpa.repository.StopJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalStopService {

    @Autowired
    private StopJpaRepository stopRepository;

    @Transactional
    public StopEntity createOrUpdate(StopEntity stopEntity){
        return stopRepository.save(stopEntity);
    }
}
