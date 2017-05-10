package com.rnsolutions.stumblr.service;

import com.rnsolutions.stumblr.entity.Post;
import java.util.List;

/**
 * <p>PostService interface.</p>
 *
 * @author bsneade
 * @version $Id: $
 */
public interface PostService {

    /**
     * <p>saveOrUpdate</p>
     *
     * @param post a {@link com.rnsolutions.stumblr.entity.Post} object.
     */
    public void saveOrUpdate(final Post post);

    /**
     * <p>delete</p>
     *
     * @param post a {@link com.rnsolutions.stumblr.entity.Post} object.
     */
    public void delete(final Post post);

    /**
     * <p>getPosts</p>
     *
     * @param offset a int.
     * @param size a int.
     * @return a {@link java.util.List} object.
     */
    public List<Post> getPosts(int offset, int size);
    
}
