package org.hillel.service;

import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.hibernate.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    @Transactional
    public Long createJourney(final JourneyEntity entity){
        return journeyRepository.create(entity);
    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> getById(Long id) {
        return journeyRepository.findById(id);
    }
}

