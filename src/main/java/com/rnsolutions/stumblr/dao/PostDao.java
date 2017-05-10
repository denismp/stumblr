package com.rnsolutions.stumblr.dao;

import com.rnsolutions.core.dao.GenericDao;
import com.rnsolutions.stumblr.entity.Post;
import java.util.List;
import java.util.Map;

/**
 * <p>PostDao interface.</p>
 *
 * @author bsneade
 * @version $Id: $
 */
public interface PostDao extends GenericDao<Post, Long> {

    /**
     * <p>search</p>
     *
     * @param searchCriteria a {@link java.util.Map} object.
     * @return a {@link java.util.List} object.
     */
    public List<Post> search(final Map<String, Object> searchCriteria);
    
}
