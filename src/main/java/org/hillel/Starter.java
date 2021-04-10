package org.hillel;
import org.hillel.config.RootConfig;
import org.hillel.homework_2.Data;
import org.hillel.homework_2.HibernateService;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws Exception {

        System.out.println("HomeWork2");

        final HibernateService HS = new HibernateService(new Data());
        HS.find("Odessa", "Lviv", LocalDate.of(2021, 03,10), LocalDate.of(2021, 03,11));
        HS.find("Odessa", "Kiev", LocalDate.of(2021, 03,10), LocalDate.of(2021, 03,11));
        HS.find("Kiev", "Lviv", LocalDate.of(2021, 03,12), LocalDate.of(2021, 03,13));
        HS.find("Kiev", "Odessa", LocalDate.of(2021, 03,15), LocalDate.of(2021, 03,16));

    }
}
