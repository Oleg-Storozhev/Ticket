package org.hillel;
import org.hillel.config.RootConfig;
import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.hibernate.entities.StopAddInfoEntity;
import org.hillel.hibernate.entities.StopEntity;
import org.hillel.hibernate.entities.VehicalEntity;
import org.hillel.hibernate.enums.DirectionType;
import org.hillel.hibernate.util.CommonInfo;
import org.hillel.homework_1.inMemoryJourneyServiceTable;
import org.hillel.homework_2.Data;
import org.hillel.homework_2.HibernateService;
import org.hillel.service.JourneyService;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws Exception {
        // ASK!!!
        System.out.println("Start");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        final JourneyService journeyservice = applicationContext.getBean("inMemoryJourneyService", JourneyService.class);
        System.out.println(JourneyService.class);
        System.out.println(TicketClient.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)));
        // ((ClassPathXmlApplicationContext) applicationContext).close();

        // --------------------------------------------------------------------------------------------------------

        System.out.println("HomeWork");

        final inMemoryJourneyServiceTable inMemory = new inMemoryJourneyServiceTable();
        inMemory.find("Odessa", "Lviv");
        inMemory.find("Odessa", "Kiev");
        inMemory.find("Kiev", "Lviv");
        inMemory.find("Kiev", "Odessa");

        // --------------------------------------------------------------------------------------------------------

        System.out.println("HomeWork2");

        final HibernateService HS = new HibernateService(new Data());
        HS.find("Odessa", "Lviv", LocalDate.of(2021, 03,10), LocalDate.of(2021, 03,11));
        HS.find("Odessa", "Kiev", LocalDate.of(2021, 03,10), LocalDate.of(2021, 03,11));
        HS.find("Kiev", "Lviv", LocalDate.of(2021, 03,12), LocalDate.of(2021, 03,13));
        HS.find("Kiev", "Odessa", LocalDate.of(2021, 03,15), LocalDate.of(2021, 03,16));

        // --------------------------------------------------------------------------------------------------------

        // FINISH IN 4 AND 5 VIDEO!
        System.out.println("Hibernate");

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Kiev");
        journeyEntity.setStationTo("Lviv");
        journeyEntity.setDateFrom(Instant.now());
        journeyEntity.setDateTo(Instant.now().plusMillis(1000000L));
        journeyEntity.setDirection(DirectionType.UNKNOWN);
        journeyEntity.setActive(false);

        final VehicalEntity vechicalEntity = new VehicalEntity();
        vechicalEntity.setName("Bus_1");
        journeyEntity.addVechicle(vechicalEntity);

        ticketClient.createJourney(journeyEntity);

        StopAddInfoEntity stopAddInfoEntity = new StopAddInfoEntity();
        stopAddInfoEntity.setLatitude(11.0);
        stopAddInfoEntity.setLongitude(1.3);
        StopEntity stopEntity = new StopEntity();
        stopEntity.setCommonInfo(new CommonInfo());
        ticketClient.createStop(stopEntity);

        System.out.println("create Journey with id = " + ticketClient.getjourneyById((journeyEntity.getId())));

    }
}