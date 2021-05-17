package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.hibernate.entities.StopAddInfoEntity;
import org.hillel.hibernate.entities.StopEntity;
import org.hillel.hibernate.entities.VehicleEntity;
import org.hillel.hibernate.enums.DirectionType;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;

public class Starter {
    public static void main(String[] args){
        System.out.println("Start");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        System.out.println("after init");

        VehicleEntity vehicleEntity = buildVehicle("bus1");
        vehicleEntity = ticketClient.createOrUpdateVehicle(vehicleEntity);
        JourneyEntity journeyEntity = buildJourney("from 1", "to 1", Instant.now(), Instant.now().plusSeconds(1000l));
        vehicleEntity.setName("bus 2");
        journeyEntity.addVechicle(vehicleEntity);
        ticketClient.createOrUpdateJourney(journeyEntity);

        System.out.println("delete vehicle");
        ticketClient.removeVehicle(vehicleEntity);
        ticketClient.removeVehicleByID(0L);

        System.out.println("delete stop");
        StopEntity stopEntity = buildStop(12.0, 13.0);
        ticketClient.removeVehicleStop(stopEntity);
        ticketClient.removeVehicleStopByID(0L);

        System.out.println("HW 5");
        System.out.println(ticketClient.findAllVehicles());
        System.out.println(ticketClient.findAllVehiclesAsNative());
        System.out.println(ticketClient.findAllVehiclesAsNamed());
        System.out.println(ticketClient.findAllVehiclesAsCriteria());
        System.out.println(ticketClient.findAllVehiclesAsStoredProcedure());

        System.out.println(ticketClient.findAllStops());
        System.out.println(ticketClient.findAllStopsAsNative());
        System.out.println(ticketClient.findAllStopsAsNamed());
        System.out.println(ticketClient.findAllStopsAsCriteria());
        System.out.println(ticketClient.findAllStopsAsStoredProcedure());

        System.out.println(ticketClient.findAllJourneys());
        System.out.println(ticketClient.findAllJourneysAsNative());
        System.out.println(ticketClient.findAllJourneysAsNamed());
        System.out.println(ticketClient.findAllJourneysAsCriteria());
        System.out.println(ticketClient.findAllJourneysAsStoredProcedure());

        System.out.println(ticketClient.findVehicleById(1L, true));
        System.out.println(ticketClient.findAllVehicles());
        System.out.println(ticketClient.findByids(1L, 2L, 3L,4L,5L));

/*        ticketClient.removeById(journeyEntity.getId());

        journeyEntity = ticketClient.createOrUpdateJourney(journeyEntity);
        journeyEntity.addStop(buildStop(1D, 2D));

        System.out.println("call create journey");
        journeyEntity = ticketClient.createOrUpdateJourney(journeyEntity);
        journeyEntity.getStops().get(0).setActive(false);
        journeyEntity.addStop(buildStop(2D,3D));
        System.out.println("call");
        ticketClient.createOrUpdateJourney(journeyEntity);*/


    }
    private static JourneyEntity buildJourney(final String from, final String to, final Instant dateFrom, final Instant dateTo){
        final JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom(from);
        journeyEntity.setStationTo(to);
        journeyEntity.setDateFrom(dateFrom);
        journeyEntity.setDateTo(dateTo);
        journeyEntity.setDirection(DirectionType.TO);
        journeyEntity.setActive(true);
        return journeyEntity;
    }

    private static StopEntity buildStop(final Double lat, final Double lon){
        final StopAddInfoEntity stopAddInfoEntity = new StopAddInfoEntity();
        stopAddInfoEntity.setLatitude(lat);
        stopAddInfoEntity.setLongitude(lon);
        StopEntity stopEntity = new StopEntity();
        stopEntity.addAddInfo(stopAddInfoEntity);
        return stopEntity;
    }

    private static VehicleEntity buildVehicle(final String name){
        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName(name);
        return vehicleEntity;
    }

}