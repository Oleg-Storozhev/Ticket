package org.hillel.hibernate.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hillel.hibernate.util.CommonInfo;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
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
        // todo ask
        ArrayList<JourneyEntity> journeys = new ArrayList<>();

        if(journeys == null) journeys = new ArrayList<>();
        journeys.add(journeyEntity);
    }
}
