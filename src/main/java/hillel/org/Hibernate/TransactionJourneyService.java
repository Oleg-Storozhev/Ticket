package hillel.org.Hibernate;

import hillel.org.Journey;
import hillel.org.Service.JourneyService;
import hillel.org.Hibernate.Entities.JourneyEntity;
import hillel.org.Hibernate.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;

@Service(value = "transactionJourneyService")
public class TransactionJourneyService implements JourneyService {

    @Autowired(required = true)
    private JourneyRepository journeyRepository;

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival) {
        return null;
    }

    @Transactional
    @Override
    public Long createJourney(final JourneyEntity entity){
        return journeyRepository.create(entity);
    }

}
