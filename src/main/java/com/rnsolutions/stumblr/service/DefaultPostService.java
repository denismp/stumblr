package com.rnsolutions.stumblr.service;

import com.rnsolutions.stumblr.dao.PostDao;
import com.rnsolutions.stumblr.entity.Post;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default Implementation of the Post Service.
 *
 * @author bsneade
 * @version $Id: $
 */
@Transactional
public class DefaultPostService implements PostService {
	private final Logger log = Logger.getLogger (this.getClass());
    
    private PostDao postDao;

    /**
     * <p>Setter for the field <code>postDao</code>.</p>
     *
     * @param postDao a {@link com.rnsolutions.stumblr.dao.PostDao} object.
     */
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    /** {@inheritDoc} */
    @Override
    public void saveOrUpdate(final Post post) {
        postDao.saveOrUpdate(post);
    }

    /** {@inheritDoc} */
    @Override
    public void delete(final Post post) {
        postDao.delete(post);
    }

    /** {@inheritDoc}
     This method enforses the offset and the size for paging.
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Post> getPosts(final int offset, final int size) {
    	log.debug("getPosts(): called with offset=" + offset + " and size=" + size );
        final Map<String, Object> emptyMap = Collections.emptyMap(); //silly generics, we can't just place the emptyMap() call in the search
        final List<Post> result = postDao.search(emptyMap);

        if( result == null ){
        	log.info("getPosts(): null result.");
        	return null;
        }
        //FIXME - need to add the offset and size to the DAO instead
        int start = offset;
        //make sure the start is not after the length of the list
        if (start >= result.size()) {
            start = result.size();
        }
        int end = start + size;
        if (end >= result.size()) {
            end = result.size();
        }
        return result.subList(start, end);
    }

}
