package org.hillel.hibernate.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.hillel.hibernate.util.CommonInfo;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StopEntity.class)
public abstract class StopEntity_ extends org.hillel.hibernate.entities.AbstractModifyEntity_ {

	public static volatile SingularAttribute<StopEntity, CommonInfo> commonInfo;
	public static volatile SingularAttribute<StopEntity, StopAddInfoEntity> stopAddInfo;

	public static final String COMMON_INFO = "commonInfo";
	public static final String STOP_ADD_INFO = "stopAddInfo";

}

