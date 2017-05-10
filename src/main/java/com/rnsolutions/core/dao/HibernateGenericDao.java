package com.rnsolutions.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.Notification;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Base Hibernate implementation for hibernate implemented DAOs.
 *
 * It is probably kind of odd, but we use the JPA annotations for configuration,
 * however, we still use the underlying hibernate SessionFactory to do
 * persistence.
 *
 * @author bsneade
 * @param <T> The Type of Entity that this DAO will be dealing with.
 * @param <PK> The Type of Primary Key this entity uses.
 * @version $Id: $
 */

public abstract class HibernateGenericDao<T extends Serializable, PK extends Serializable>
        extends HibernateDaoSupport implements GenericDao<T, PK>, NotificationPublisherAware {

    /**
     * Because Generics are erased at runtime we have to determine the type of
     * Entity this DAO implementation is dealing with on initialization
     */
    protected final Class<? extends T> type;
	private NotificationPublisher notificationPublisher;
	private final AtomicLong notificationSequence = new AtomicLong();

    /**
     * <p>Constructor for HibernateGenericDao.</p>
     *
     * Uses a bunch of reflection magic to get the class type the generic
     * represents since they are erased at run time.
     *
     * @param <T> The Type of Entity that this DAO will be dealing with.
     * @param <PK> The Type of Primary Key this entity uses.
     */
    public HibernateGenericDao() {
        super();

        // Use reflection to determine which entity class this service deals
        // with
        // Note: we have to handle the weird case of this being a cglib proxy
        Type thisType = getClass().getGenericSuperclass();
        while (thisType instanceof Class) {
            //the while loops up the heirarchy in the case that a class extends a genericised class.
            thisType = ((Class) thisType).getGenericSuperclass();
        } //end while

        final Type localType;
        if (thisType instanceof ParameterizedType) {
            localType = ((ParameterizedType) thisType).getActualTypeArguments()[0];
        } else {
            throw new IllegalArgumentException("Problem handling type construction for " + getClass());
        }

        // handled nested generics (i.e. the entity is
        // genericised)
        if (localType instanceof Class) {
            this.type = (Class<T>) localType;
        } else if (localType instanceof ParameterizedType) {
            this.type = (Class<T>) ((ParameterizedType) localType).getRawType();
        } else {
            throw new IllegalArgumentException("Problem determining the class of the generic for " + getClass());
        }
    }

    /**
     * <p>Constructor for HibernateGenericDao.</p>
     *
     * Bypasses the Generics type detection and explicitly sets the type.
     *
     * @param type a {@link java.lang.Class} object to explicitly set as the type.
     */
    protected HibernateGenericDao(final Class<? extends T> type) {
        super();
        this.type = type;
    }
    
    public void setNotificationPublisher( final NotificationPublisher notificationPublisher ){
    	this.notificationPublisher = notificationPublisher;
    }

    /** {@inheritDoc} */
    @ManagedOperation(description="saveOrUpdate")
    @ManagedOperationParameters({
    	@ManagedOperationParameter(name = "transientInstance", description = "Save the given transient instance.")
    })
    @Override
    public void saveOrUpdate(final T transientInstance) {
    	if( notificationPublisher != null ){
    		final Notification notification = new Notification("type", type, notificationSequence.getAndIncrement(), "SaveOrUpdate called.");
    		notificationPublisher.sendNotification(notification);
    	}
        getHibernateTemplate().saveOrUpdate(transientInstance);
    }

    /** {@inheritDoc} */
    @ManagedOperation(description="findById")
    @ManagedOperationParameters({
    	@ManagedOperationParameter(name = "id", description = "ID to search for.")
    })
    @Override
    public T findById(final PK id) {
    	if( notificationPublisher != null ){
    		final Notification notification = new Notification("type", type, notificationSequence.getAndIncrement(), "findById called.");
    		notificationPublisher.sendNotification(notification);
    	}

        return (T) getHibernateTemplate().get(type, id);
    }

    /** {@inheritDoc} */
    @ManagedOperation(description="search by example")
    @ManagedOperationParameters({
    	@ManagedOperationParameter(name = "example", description = "Search by the given example.")
    })
    @Override
    public List<T> search(final T example) {
    	if( notificationPublisher != null ){
    		final Notification notification = new Notification("type", type, notificationSequence.getAndIncrement(), "search by example called.");
    		notificationPublisher.sendNotification(notification);
    	}
        return search(example, type);
    }

    /** {@inheritDoc}
    Enables 'like' (with MatchMode.ANYWHERE), and 'ignoreCase'.
     */
    @ManagedOperation(description="search by example and type")
    @ManagedOperationParameters({
    	@ManagedOperationParameter(name = "example", description = "Search by the given example."),
    	@ManagedOperationParameter(name = "searchType", description = "Search by the given example.")
    })
    @Override
    public List<T> search(final T example, final Class<? extends T> searchType) {
        final Criteria criteria = getSession().createCriteria(searchType);

        final Example pExample = Example.create(example);
    	if( notificationPublisher != null ){
    		final Notification notification = new Notification("type", type, notificationSequence.getAndIncrement(), "search by example and type called.");
    		notificationPublisher.sendNotification(notification);
    	}

        pExample.enableLike();
        pExample.ignoreCase();
        pExample.enableLike(MatchMode.ANYWHERE);

        criteria.add(pExample);

        final List resultList = criteria.list();
        return resultList;
    }

    /** {@inheritDoc} */
    @ManagedOperation(description="delete")
    @ManagedOperationParameters({
    	@ManagedOperationParameter(name = "persistentInstance", description = "Delete the given instance.")
    })
    @Override
    public void delete(final T persistentInstance) {
    	if( notificationPublisher != null ){
    		final Notification notification = new Notification("type", type, notificationSequence.getAndIncrement(), "delete called.");
    		notificationPublisher.sendNotification(notification);
    	}

        getHibernateTemplate().delete(persistentInstance);
    }
}
