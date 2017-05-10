package com.rnsolutions.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Genericized DAO implementation
 *
 * @author bsneade
 * @param <T> The Entity that this DAO will be acting upon
 * @param <PK> The Primary Key type for this Entity, since entities can have many types of Primary Keys.
 * @version $Id: $
 */
public interface GenericDao<T extends Serializable, PK extends Serializable> {

    /**
     * Just that, saves or updates the instance.
     *
     * @param transientInstance a T object to be saved or updated by the perisitence mechanism the implementation uses
     * @param <T> The Entity that this DAO will be acting upon
     * @param <PK> The Primary Key type for this Entity, since entities can have many types of Primary Keys.
     */
    public void saveOrUpdate(T transientInstance);

    /**
     * Finds an instance by primary key
     *
     * @param <T> The Entity that this DAO will be acting upon
     * @param <PK> The Primary Key type for this Entity, since entities can have many types of Primary Keys.
     */
    public T findById(PK id);

    /**
     * Searches for based on the specified implementation
     *
     * @param <T> The Entity that this DAO will be acting upon
     * @return a {@link java.util.List} object with the search results.
     */
    public List<T> search(T example);

    /**
     * Searches based on the specified example or a subclass of the type (when
     * polymorphic mappings are used).
     *
     * @param <T> The Entity that this DAO will be acting upon
     * @param type a {@link java.lang.Class} object representing the class to search with.
     * @return a {@link java.util.List} object.
     */
    public List<T> search(T example, Class<? extends T> type);

    /**
     * Deletes the instance.
     *
     * @param persistentInstance a T object to delete
     */
    public void delete(T persistentInstance);
}

