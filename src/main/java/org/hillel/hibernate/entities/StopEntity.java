package org.hillel.hibernate.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hillel.hibernate.util.CommonInfo;

import javax.persistence.*;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
public class StopEntity extends AbstractModifyEntity<Long> {

    @Embedded
    private CommonInfo commonInfo;

    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST})
    private StopAddInfoEntity stopAddInfo;

    public void addAddInfo(StopAddInfoEntity stopAddInfo){
        if (stopAddInfo == null){
            this.stopAddInfo = null;
            return;
        }
        stopAddInfo.setStop(this);
        this.setStopAddInfo(stopAddInfo);
    }

}
