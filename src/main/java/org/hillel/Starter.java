package org.hillel;
import org.hillel.config.RootConfig;
import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.hibernate.entities.VehicalEntity;
import org.hillel.hibernate.enums.DirectionType;
import org.hillel.homework.inMemoryJourneyServiceTable;
import org.hillel.homework_2.HibernateService;
import org.hillel.service.JourneyService;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Instant;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws Exception {
        // ASK!!!
        System.out.println("Start");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(new Class[]{RootConfig.class});
        System.out.println("Check 1");
        final JourneyService journeyservice = applicationContext.getBean("inMemoryJourneyService", JourneyService.class);
        System.out.println(JourneyService.class);
        System.out.println(TicketClient.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        System.out.println("after init");
        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)));
        ((ClassPathXmlApplicationContext) applicationContext).close();

        System.out.println("HomeWork");

        final inMemoryJourneyServiceTable inMemory = new inMemoryJourneyServiceTable();
        inMemory.find("Odessa", "Lviv");
        inMemory.find("Odessa", "Kiev");
        inMemory.find("Kiev", "Lviv");
        inMemory.find("Kiev", "Odessa");

        // FINISH IN 4 AND 5 VIDEO!
/*        System.out.println("Hibernate");
        final VehicalEntity vechicalEntity = new VehicalEntity();


        final JourneyEntity journeyEntity = new JourneyEntity(null);
        journeyEntity.setStationFrom("Kiev");
        journeyEntity.setStationTo("Lviv");
        journeyEntity.setDateFrom(Instant.now());
        journeyEntity.setDateTo(Instant.now().plusMillis(1000000L));
        journeyEntity.setDirection(DirectionType.UNKNOWN);*/

//        System.out.println("create Journey with id = " + ticketClient.createJourney(journeyEntity));

        System.out.println("HOMEWORK_2");
        final HibernateService HS = new HibernateService();
    }
}
