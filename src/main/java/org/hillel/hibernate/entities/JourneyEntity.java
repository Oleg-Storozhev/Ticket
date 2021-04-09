package org.hillel.hibernate.entities;

import org.hillel.hibernate.enums.DirectionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table (name = "DBTickets")
@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "date_from", nullable = false)
    private Instant dateFrom;

    @Column(name = "date_to", nullable = false)
    private Instant dateTo;

    @Column(name = "direction", length = 20)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "veh_id")
    private VehicalEntity vehicle;

    public void addVechicle(final VehicalEntity vehicle){
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JourneyEntity.class.getSimpleName() + "[", "]")
                .add("stationFrom='"+ stationFrom + "'")
                .add("stationTo='"+ stationTo + "'")
                .add("dateFrom='"+ dateFrom + "'")
                .add("dateTo='"+ dateTo + "'")
                .add("direction='"+ direction + "'")
                //.add("vehicle='"+ vehicle + "'")
                .toString();
    }
}