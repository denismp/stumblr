package com.rnsolutions.core.dao;

import java.io.Serializable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bsneade
 */
public class HibernateGenericDaoTest {

    public HibernateGenericDaoTest() {
    }

    @Test
    public void testConstructor() {
        final MockHibernateGenericDao mockHibernateGenericDao = new MockHibernateGenericDao();
        assertEquals(MockEntity.class, mockHibernateGenericDao.getType());
        assertEquals(MockEntity.class.getName(), mockHibernateGenericDao.getType().getName());
    }

    @Test
    public void testConstructor_withGenericEntity() {
        final GenericMockHibernateGenericDao genericMockHibernateGenericDao = new GenericMockHibernateGenericDao();
        assertEquals(GenericMockEntity.class, genericMockHibernateGenericDao.getType());
        assertEquals(GenericMockEntity.class.getName(), genericMockHibernateGenericDao.getType().getName());
    }

    public class MockEntity implements Serializable {

    }

    public class MockHibernateGenericDao extends HibernateGenericDao<MockEntity, Long> {
        public Class<?> getType() {
            return this.type;
        }
    }

    public class GenericMockEntity<MockEntity> implements Serializable {

    }

    public class GenericMockHibernateGenericDao extends HibernateGenericDao<GenericMockEntity, Long> {
        public Class<?> getType() {
            return this.type;
        }
    }

}