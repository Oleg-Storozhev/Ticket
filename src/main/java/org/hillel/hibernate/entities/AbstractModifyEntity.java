package org.hillel.hibernate.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hillel.hibernate.util.YesNoConventer;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractModifyEntity<D extends Serializable> implements Persistable<D> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private D id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @Column(name = "active")
    @Convert(converter = YesNoConventer.class)
    private boolean active = true;

    @Override
    public boolean isNew(){
        return id == null;
    }

}
