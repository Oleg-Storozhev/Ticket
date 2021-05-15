package org.hillel.hibernate.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "findAllVehicles",
                query = "from VehicleEntity")
})

@NamedStoredProcedureQueries(value = {
        @NamedStoredProcedureQuery(
                name = "findAllVehicles",
                procedureName = "find_all_vehicles",
                parameters = @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = Class.class),
                resultClasses = VehicleEntity.class
        )
})
public class VehicleEntity extends AbstractModifyEntity<Long> {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.REMOVE})
    private Set<JourneyEntity> journeys = new HashSet<>();

    public void addJourney(final JourneyEntity journeyEntity){
        if (journeys == null){
            journeys = new HashSet<>();
        }
        journeys.add(journeyEntity);
        journeyEntity.addVechicle(this);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JourneyEntity.class.getSimpleName() + "[", "]")
                .add("id='"+ getId() + "'")
                .add("name='" + name +"'")
                .toString();
    }

    public void removedAllJourneys(){
        if(CollectionUtils.isEmpty(journeys)) return;
        journeys.forEach(item-> item.setVehicle(null));
    }

}
