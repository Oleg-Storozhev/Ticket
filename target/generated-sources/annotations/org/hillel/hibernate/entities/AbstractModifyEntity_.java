package org.hillel.hibernate.entities;

import java.io.Serializable;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractModifyEntity.class)
public abstract class AbstractModifyEntity_ {

	public static volatile SingularAttribute<AbstractModifyEntity, Boolean> active;
	public static volatile SingularAttribute<AbstractModifyEntity, Serializable> id;
	public static volatile SingularAttribute<AbstractModifyEntity, Instant> createDate;

	public static final String ACTIVE = "active";
	public static final String ID = "id";
	public static final String CREATE_DATE = "createDate";

}

