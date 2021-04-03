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
public class JourneyEntity extends AbstractModifyEntity<Long> {

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

}