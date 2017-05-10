package com.rnsolutions.stumblr.dao;

import com.rnsolutions.core.dao.HibernateGenericDao;
import com.rnsolutions.stumblr.entity.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Hibernate implementation for persisting {@link Post}s.
 *
 * @author bsneade
 * @version $Id: $
 */
@ManagedResource(description="Hibernate implementation of the DAO for Post")
public class HibernatePostDao extends HibernateGenericDao<Post, Long>
    implements PostDao {

    /** {@inheritDoc}
     Searches on... (TODO - what?)
     */
    @ManagedOperation(description="search by searchCriteria")
    @ManagedOperationParameters({
    	@ManagedOperationParameter(name = "searchCriteria", description = "Search by the given searchCriteria.")
    })
    @Override
    public List<Post> search(Map<String, Object> searchCriteria) {
        //create a list of searched keys for some bookkeeping
        final List<String> searchedKeys = new ArrayList<String>(searchCriteria.keySet().size());

        //create a criteria to apply the search to
        final Criteria criteria =
                getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Post.class);

        //add our search conditions
        //TODO - we don't have any requirements for a specifc search yet :(
        //       maybe the query size restictions could go here
//        if (searchCriteria.containsKey("")) {
//            searchedKeys.add("");
//        }

        //check if we missed any search critieria
        if (!CollectionUtils.subtract(searchCriteria.keySet(), searchedKeys).isEmpty()) {
            //TODO - log the error.
            //TODO - bonus, expose error stats to JMX
        }

        //by deafult sort in reverse order by date
        criteria.addOrder(Order.desc("createDate"));

        return criteria.list();
    }

}
