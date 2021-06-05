package org.hillel.service;

import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.hibernate.jpa.repository.JourneyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionalJourneyService {

    @Autowired
    private JourneyJpaRepository journeyRepository;

    @Transactional
    public JourneyEntity createOrUpdateJourney(final JourneyEntity entity1){
        return journeyRepository.save(entity1);
    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> findById(Long id, boolean withDepedencies) {
        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if(withDepedencies && byId.isPresent()){
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getName();
        }
        return byId;
    }


    @Transactional
    public void remove(JourneyEntity journeyEntity) {
        journeyRepository.delete(journeyEntity);
    }
    @Transactional
    public void removeByID(Long ID) {
        journeyRepository.deleteById(ID);
    }

    public Collection<JourneyEntity> findAll() {
        return (Collection<JourneyEntity>) journeyRepository.findAll();
    }

    public Collection<JourneyEntity> findAllSortedByID(PageRequest of) {
        return journeyRepository.findAllSortedByID(of);
    }

    public Collection<JourneyEntity> findAllSortedByActive(PageRequest of) {
        return journeyRepository.findAllSortedByActive(of);
    }
}

