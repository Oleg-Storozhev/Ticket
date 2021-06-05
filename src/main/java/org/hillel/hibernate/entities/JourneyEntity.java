package org.hillel.hibernate.entities;

import org.hibernate.annotations.DynamicUpdate;
import org.hillel.hibernate.enums.DirectionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table (name = "journey", uniqueConstraints = @UniqueConstraint(name = "uniq_station_from_to", columnNames = {"stationFrom", "stationTo"}))
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class JourneyEntity extends AbstractModifyEntity<Long> {

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneyEntity)) return false;
        JourneyEntity entity = (JourneyEntity) o;
        return getId() != null && Objects.equals(getId(),entity.getId());
    }

    @Column(name = "station_from", length = 50, nullable = false, columnDefinition = "varchar(100) default 'NONE'")
    private String stationFrom;

    @Column(name = "station_to", length = 50, nullable = false, columnDefinition = "varchar(100) default 'NONE'")
    private String stationTo;

    @Column(name = "departure", nullable = false)
    private Instant dateFrom;

    @Column(name = "arrival", nullable = false)
    private Instant dateTo;

    @Column(name = "direction", length = 20)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "vehId")
    private VehicleEntity vehicle;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "journey_stop",
            indexes = @Index(name = "journey_stop_idx", columnList = "journey_id, stop_id"),
            joinColumns = @JoinColumn(name = "journey_id"),
            inverseJoinColumns =  @JoinColumn(name = "stop_id")
    )
    private List<StopEntity> stops = new ArrayList<>();

    public void addVechicle(final VehicleEntity vehicle){
        this.vehicle = vehicle;
    }

    public void addStop(StopEntity stop){
        if(stop == null) return;
        if (stops == null) stops = new ArrayList<>();
        stops.add(stop);
        stop.addJourney(this);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JourneyEntity.class.getSimpleName() + "[", "]")
                .add("stationFrom='"+ stationFrom + "'")
                .add("stationTo='"+ stationTo + "'")
                .add("dateFrom='"+ dateFrom + "'")
                .add("dateTo='"+ dateTo + "'")
                .add("direction='"+ direction + "'")
                .add("vehicle='"+ vehicle + "'")
                .toString();
    }
}