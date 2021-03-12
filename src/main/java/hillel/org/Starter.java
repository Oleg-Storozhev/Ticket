package hillel.org;
import hillel.org.Config.RootConfig;
import hillel.org.Service.JourneyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        //AppContext.load("Aplication propertis.properties");

        // final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        System.out.println("after init");
        final JourneyService journeyservice = applicationContext.getBean("inMemoryJourneyService2", JourneyService.class);
        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyservice.find("Odessa", "Lviv", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)));
    }
}
