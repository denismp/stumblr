package com.rnsolutions.stumblr.dao;

import com.rnsolutions.stumblr.entity.Post;
import com.rnsolutions.stumblr.entity.TextPost;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.junit.Assert.*;

/**
 *
 * @author bsneade
 */
@ContextConfiguration(locations = {
    "classpath:com/rnsolutions/stumblr/dao/HibernatePostDaoTest-applicationContext.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager")
public class HibernatePostDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    protected HibernatePostDao postDao;

    @Test
    public void testSaveOrUpdate_createSomeData() {
        final TextPost textPost = new TextPost();
        textPost.setText("Today was an interesting day");

        postDao.saveOrUpdate(textPost);

        assertNotNull("Should have been assigned an ID", textPost.getId());
    }

    @Test
    public void testSearch_withNoCritieria() {
        //set up test data
        final TextPost textPost = new TextPost();
        textPost.setText("Today was an interesting day");
        postDao.saveOrUpdate(textPost);

        //run the search
        final List<Post> result = postDao.search(new HashMap<String, Object>(0));

        //from the previous test method
        assertNotNull("Should have some results", result);
        assertEquals("Wrong number of results", 1, result.size());
        assertTrue("Found the wrong object", result.get(0) instanceof TextPost); 
    }

}
