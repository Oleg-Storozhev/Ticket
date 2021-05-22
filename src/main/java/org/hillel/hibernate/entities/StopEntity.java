package org.hillel.hibernate.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hillel.hibernate.util.CommonInfo;

import javax.persistence.*;
import java.util.ArrayList;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@NamedQueries(value = {
        @NamedQuery(name = "findAllStops",
                query = "from StopEntity")
})
@NamedStoredProcedureQueries(value = {
        @NamedStoredProcedureQuery(
                name = "findAllStops",
                procedureName = "find_all_stops",
                parameters = @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = Class.class),
                resultClasses = StopEntity.class
        )
})
public class StopEntity extends AbstractModifyEntity<Long> {

    @Embedded
    private CommonInfo commonInfo;

    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private StopAddInfoEntity stopAddInfo;


    public void addAddInfo(StopAddInfoEntity stopAddInfo){
        if (stopAddInfo == null){
            this.stopAddInfo = null;
            return;
        }
        stopAddInfo.setStop(this);
        this.setStopAddInfo(stopAddInfo);
    }

    public void addJourney(JourneyEntity journeyEntity) {
        if(journeyEntity == null) return;
        ArrayList<JourneyEntity> journeys = new ArrayList<>();

        if(journeys == null) journeys = new ArrayList<>();
        journeys.add(journeyEntity);
    }
}
