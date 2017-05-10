package com.rnsolutions.stumblr.dwr;

import com.rnsolutions.stumblr.service.PostService;
import java.util.HashMap;
import java.util.Map;

/**
 * DWR for fetching more posts
 *
 * @author bsneade
 */
public class PostDwr {

    private PostService postService;

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public Map<String, String> getMorePosts(int offset) {
        final Map<String, String> returnMap = new HashMap<String, String>(2);

        //we only want 5, but we need to know if we need to "more post" link
        postService.getPosts(offset, 6);

        return returnMap;
    }

}
