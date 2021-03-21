package hillel.org.Hibernate.persistance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "DBTickets")


@Getter
@Setter
@NoArgsConstructor
@ToString
public class JourneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "station_from", length = 1000, nullable = false, columnDefinition = "varchar(100) default 'NO_DATA'")
    private String stationFrom;

    @Column (name = "station_To", length = 1000, nullable = false, columnDefinition = "varchar(100) default 'NO_DATA'")
    private String stationTo;

    @Column (name = "DateFrom", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column (name = "DateTo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTo;
}