package hillel.org;
import hillel.org.HOMEWORK.inMemoryJourneyServiceTable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, SQLException {
        // ASK!!!
        /*System.out.println("Start");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        final JourneyService journeyservice = applicationContext.getBean("inMemoryJourneyService2", JourneyService.class);
        System.out.println(JourneyService.class);
        System.out.println(TicketClient.class);
        final TicketClient ticketClient = applicationContext.getBean(TicketClient.class);*/

        /*System.out.println("after init");
        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)));*/
        // ((ClassPathXmlApplicationContext) applicationContext).close();

        System.out.println("HomeWork");

        final inMemoryJourneyServiceTable inMemory = new inMemoryJourneyServiceTable();
        inMemory.find("Odessa", "Lviv");
        inMemory.find("Odessa", "Kiev");
        inMemory.find("Kiev", "Lviv");
        inMemory.find("Kiev", "Odessa");

        // FINISH IN 4 AND 5 VIDEO!
        /*System.out.println("Hibernate");
        final VehicalEntity vechicalEntity = new VehicleEntity();


        final JourneyEntity journeyEntity = new JourneyEntity(null);
        journeyEntity.setStationFrom("Kiev");
        journeyEntity.setStationTo("Lviv");
        journeyEntity.setDateFrom(Instant.now());
        journeyEntity.setDateTo(Instant.now().plusMillis(1000000L));
        journeyEntity.setDirection(DirectionType.UNKNOWN);*/

        // System.out.println("create Journey with id = " + ticketClient.createJourney(journeyEntity));

        System.out.println("HOMEWORK_2");
        // final HibernateService HS = new HibernateService();
    }
}
