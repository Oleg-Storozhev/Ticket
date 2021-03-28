package hillel.org.Hibernate.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class VehicleEntity extends AbstractModifyEntity<Long> {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vehicleEntity")
    private Set<JourneyEntity> journeys = new HashSet<>();

    public void addJourney(final JourneyEntity journeyEntity){
        if (journeys == null){
            journeys = new HashSet<>();
        }
        journeys.add(journeyEntity);
        journeyEntity.addvehicle(this);
    }
}
