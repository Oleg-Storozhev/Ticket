package org.hillel.homework_2;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TicketsEntity.class)
public abstract class TicketsEntity_ {

	public static volatile SingularAttribute<TicketsEntity, String> StationFrom;
	public static volatile SingularAttribute<TicketsEntity, LocalDate> arrival;
	public static volatile SingularAttribute<TicketsEntity, Long> id;
	public static volatile SingularAttribute<TicketsEntity, LocalDate> departure;
	public static volatile SingularAttribute<TicketsEntity, String> StationTo;

	public static final String STATION_FROM = "StationFrom";
	public static final String ARRIVAL = "arrival";
	public static final String ID = "id";
	public static final String DEPARTURE = "departure";
	public static final String STATION_TO = "StationTo";

}

