package org.hillel.hibernate.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StopAddInfoEntity.class)
public abstract class StopAddInfoEntity_ {

	public static volatile SingularAttribute<StopAddInfoEntity, StopEntity> stop;
	public static volatile SingularAttribute<StopAddInfoEntity, Double> latitude;
	public static volatile SingularAttribute<StopAddInfoEntity, Long> id;
	public static volatile SingularAttribute<StopAddInfoEntity, Double> longitude;

	public static final String STOP = "stop";
	public static final String LATITUDE = "latitude";
	public static final String ID = "id";
	public static final String LONGITUDE = "longitude";

}

