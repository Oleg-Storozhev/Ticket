package org.hillel.hibernate.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VehicleEntity.class)
public abstract class VehicleEntity_ extends org.hillel.hibernate.entities.AbstractModifyEntity_ {

	public static volatile SetAttribute<VehicleEntity, JourneyEntity> journeys;
	public static volatile SingularAttribute<VehicleEntity, String> name;

	public static final String JOURNEYS = "journeys";
	public static final String NAME = "name";

}

