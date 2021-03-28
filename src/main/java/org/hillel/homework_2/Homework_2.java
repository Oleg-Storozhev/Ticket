package org.hillel.homework_2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@Table (name = "Homework_2")
public class Homework_2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "StatonFrom")
    private String StationFrom;

    @Column(name = "StatonTo")
    private String StationTo;

    @Column(name = "Arrival")
    private LocalDate arrival;

    @Column(name = "Departure")
    private LocalDate departure;

}
