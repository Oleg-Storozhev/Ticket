package org.hillel.service;

import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.hibernate.jpa.repository.JourneyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionalJourneyService {

    @Autowired
    private JourneyJpaRepository journeyRepository;

    @Transactional
    public JourneyEntity createOrUpdateJourney(final JourneyEntity entity1){
        System.out.println("create journey ");
        final JourneyEntity orUpdate = journeyRepository.save(entity1);
        System.out.println("get journey by id");
        JourneyEntity journey = journeyRepository.findById(orUpdate.getId()).get();
        journeyRepository.getEntitymanager().flush();
        if(entity1.getId() == 1){
            throw new IllegalArgumentException();
        }
        System.out.println("remove journey by id");
        assert journey.getId() != null;
        journeyRepository.deleteById(journey.getId());

        JourneyEntity entity2 = new JourneyEntity();
        entity2.setDateFrom(orUpdate.getDateFrom());
        entity2.setDateTo(orUpdate.getDateTo());
        entity2.setStationFrom(orUpdate.getStationFrom());
        entity2.setStationTo(orUpdate.getStationTo());
        entity2.setActive(false);
        return journeyRepository.save(entity2);
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
}

