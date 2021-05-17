package org.hillel.service;

import org.hillel.hibernate.entities.StopEntity;
import org.hillel.hibernate.entities.JourneyEntity;
import org.hillel.Journey;
import org.hillel.hibernate.entities.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.*;

@Component
public class TicketClient {

    @Autowired
    private List<JourneyService> journeyServices;

    @Autowired
    private TransactionalJourneyService journeyService;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private Environment environment;

    @Autowired
    private TransnationalVehicleService vehicleService;

    @Value("${datasource.url}")
    private String url;

    public TicketClient(){
    }

    public Optional<JourneyEntity> getjourneyById(Long id, boolean withDependencies){
//        Assert.notNull(id, "id must be a set");
        return id == null? Optional.empty() : journeyService.findById(id, withDependencies);
    }

    public StopEntity createOrUpdateStop(final StopEntity stopEntity){
        return stopService.createOrUpdate(stopEntity);
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) throws Exception {
        if (!StringUtils.hasText(stationFrom))
            throw new IllegalArgumentException("stationFrom must be set");
        if (!StringUtils.hasText(stationTo))
            throw new IllegalArgumentException("stationTo must be set");
        if (dateFrom == null)
            throw new IllegalArgumentException("dateFrom must be set");
        if (dateTo == null)
            throw new IllegalArgumentException("dateTo must be set");

        for (JourneyService service : journeyServices){
            System.out.println(service.getClass().getName());
        }
        return Collections.emptyList();
    }

    @PostConstruct
    public void doPost(){
        System.out.println("post construct");
        //System.out.println(environment.getProperty("datasource.url"));
        System.out.println(url);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy");
    }

    public JourneyEntity createOrUpdateJourney(JourneyEntity journey) {
        return journeyService.createOrUpdateJourney(journey);
    }

    public VehicleEntity createOrUpdateVehicle(VehicleEntity vehicalEntity){
        return vehicleService.createOrUpdate(vehicalEntity);
    }

    public void remove(JourneyEntity journeyEntity) {
        journeyService.remove(journeyEntity);
    }
    public void removeById(Long ID) {
        journeyService.removeByID(ID);
    }

    public void removeVehicle(final VehicleEntity vehicalEntity){
        vehicleService.remove(vehicalEntity);
    }

    public void removeVehicleByID(Long ID) {
        vehicleService.removeByID(ID);
    }

    public Collection<VehicleEntity> findByids(Long ... ids){
        return vehicleService.findByIds(ids);
    }

    public Optional<VehicleEntity> findVehicleById(Long id, boolean withDep){
        return vehicleService.findById(id, withDep);
    }
    // FIND VEHICLES
    public Collection<VehicleEntity> findAllVehicles(){
        return vehicleService.findAll();
    }
    public Collection<VehicleEntity> findAllVehiclesAsNative(){
        return vehicleService.findAllAsNatie();
    }
    public Collection<VehicleEntity> findAllVehiclesAsNamed(){
        return vehicleService.findAllVehiclesAsNamed();
    }
    public Collection<VehicleEntity> findAllVehiclesAsCriteria(){
        return vehicleService.findAllVehiclesAsCriteria();
    }
    public Collection<VehicleEntity> findAllVehiclesAsStoredProcedure(){
        return vehicleService.findAllVehiclesAsStoredProcedure();
    }
    // FIND STOPS
    public Collection<StopEntity> findAllStops(){
        return stopService.findAll();
    }
    public Collection<StopEntity> findAllStopsAsNative(){
        return stopService.findAllAsNatie();
    }
    public Collection<StopEntity> findAllStopsAsNamed(){
        return stopService.findAllVehiclesAsNamed();
    }
    public Collection<StopEntity> findAllStopsAsCriteria(){
        return stopService.findAllVehiclesAsCriteria();
    }
    public Collection<StopEntity> findAllStopsAsStoredProcedure(){
        return stopService.findAllVehiclesAsStoredProcedure();
    }
    // FIND Journeys
    public Collection<JourneyEntity> findAllJourneys(){
        return journeyService.findAll();
    }
    public Collection<JourneyEntity> findAllJourneysAsNative(){
        return journeyService.findAllAsNatie();
    }
    public Collection<JourneyEntity> findAllJourneysAsNamed(){
        return journeyService.findAllVehiclesAsNamed();
    }
    public Collection<JourneyEntity> findAllJourneysAsCriteria(){
        return journeyService.findAllVehiclesAsCriteria();
    }
    public Collection<JourneyEntity> findAllJourneysAsStoredProcedure(){
        return journeyService.findAllVehiclesAsStoredProcedure();
    }


    public void removeVehicleStop(StopEntity stopEntity){
        stopService.remove(stopEntity);
    }

    public void removeVehicleStopByID(Long ID) {
        stopService.removeByID(ID);
    }

    public List<VehicleEntity> getAllVehicles(Integer pageNo, Integer pageSize, String sortBy) {
        return vehicleService.getAllVehicles(pageNo, pageSize, sortBy);
    }

    public List<StopEntity> getAllStops(Integer pageNo, Integer pageSize, String sortBy) {
        return stopService.getAllStops(pageNo, pageSize, sortBy);
    }

    public List<JourneyEntity> getAllJourneys(Integer pageNo, Integer pageSize, String sortBy) {
        return journeyService.getAllJourneys(pageNo, pageSize, sortBy);
    }

}
