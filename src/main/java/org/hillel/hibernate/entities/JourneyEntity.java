package org.hillel.hibernate.entities;

import org.hillel.hibernate.enums.DirectionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table (name = "DBTickets")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class JourneyEntity extends AbstractModifyEntity<Long>{

    @Column (name = "Station_from", length = 1000, nullable = false, columnDefinition = "varchar(100) default 'NO_DATA'")
    private String stationFrom;

    @Column (name = "Station_to", length = 1000, nullable = false, columnDefinition = "varchar(100) default 'NO_DATA'")
    private String stationTo;

    @Column (name = "Date_from", nullable = false)
    @Temporal(TemporalType.DATE)
    private Instant dateFrom;

    @Column (name = "Date_to", nullable = false)
    @Temporal(TemporalType.DATE)
    private Instant dateTo;

    @Column(name = "direction", length = 20)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "veh_id")
    private VehicleEntity vehicleEntity;

    public  void addvehicle(final VehicleEntity vehicleEntity){
        this.vehicleEntity = vehicleEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneyEntity)) return false;
        JourneyEntity that = (JourneyEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

/*    @ManyToMany
    @JoinTable(name = "journey_stop", joinColumns = @JoinColumn(name = "join_id"),)
    private List<StopEntity> stops = new ArrayList<>();*/

    @Override
    public String toString() {
        return "JourneyEntity{" +
                "stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", direction=" + direction +
                ", vehicleEntity=" + vehicleEntity +
                '}';
    }
}