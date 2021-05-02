package org.hillel.hibernate.entities;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.hillel.hibernate.enums.DirectionType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JourneyEntity.class)
public abstract class JourneyEntity_ extends org.hillel.hibernate.entities.AbstractModifyEntity_ {

	public static volatile SingularAttribute<JourneyEntity, Instant> dateTo;
	public static volatile ListAttribute<JourneyEntity, StopEntity> stops;
	public static volatile SingularAttribute<JourneyEntity, Instant> dateFrom;
	public static volatile SingularAttribute<JourneyEntity, String> stationFrom;
	public static volatile SingularAttribute<JourneyEntity, String> stationTo;
	public static volatile SingularAttribute<JourneyEntity, DirectionType> direction;
	public static volatile SingularAttribute<JourneyEntity, VehicleEntity> vehicle;

	public static final String DATE_TO = "dateTo";
	public static final String STOPS = "stops";
	public static final String DATE_FROM = "dateFrom";
	public static final String STATION_FROM = "stationFrom";
	public static final String STATION_TO = "stationTo";
	public static final String DIRECTION = "direction";
	public static final String VEHICLE = "vehicle";

}

