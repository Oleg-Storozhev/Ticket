package hillel.org;
import hillel.org.Config.RootConfig;
import hillel.org.Hibernate.persistance.JourneyEntity;
import hillel.org.Service.JourneyService;
import hillel.org.HOMEWORK.inMemoryJourneyServiceTable;
import hillel.org.Service.TicketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        System.out.println("Start");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        final JourneyService journeyservice = applicationContext.getBean("inMemoryJourneyService2", JourneyService.class);
        System.out.println(JourneyService.class);
        System.out.println(TicketClient.class);
        // TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        /*System.out.println("after init");
        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)));*/
        // ((ClassPathXmlApplicationContext) applicationContext).close();

        /*System.out.println("HomeWork");

        final inMemoryJourneyServiceTable inMemory = new inMemoryJourneyServiceTable();
        inMemory.find("Odessa", "Lviv");
        inMemory.find("Odessa", "Kiev");
        inMemory.find("Kiev", "Lviv");
        inMemory.find("Kiev", "Odessa");*/

        System.out.println("Hibernate");
        final JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Kiev");
        journeyEntity.setStationTo("Lviv");
        journeyEntity.setDateFrom(Date.from(Instant.now()));
        journeyEntity.setDateTo(Date.from(Instant.now()));
        //System.out.println("create Journey with id = " + ticketClient.createJourney(journeyEntity));
        
    }
}
