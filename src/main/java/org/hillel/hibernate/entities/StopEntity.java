package org.hillel.hibernate.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table (name = "Stop")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StopEntity extends AbstractModifyEntity<Long> {
}
