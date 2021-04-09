/*
package org.hillel.hibernate.repository;

import com.sun.xml.bind.v2.model.core.ID;
import org.hillel.hibernate.entities.AbstractModifyEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class CommonRepository extends AbstractModifyEntity<ID, ID extends Serializable> implements GenericRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public E createOrUpdate(E entity){
        Assert.notNull(entity, "Entity have to be a set");
    }

}
*/
