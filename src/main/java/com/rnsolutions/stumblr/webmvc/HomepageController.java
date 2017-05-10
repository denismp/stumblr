package com.rnsolutions.stumblr.webmvc;

import com.rnsolutions.stumblr.entity.Post;
import com.rnsolutions.stumblr.service.PostService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

/**
 * Controller for the home page.  Should load up the first 10 posts.
 *
 * @author bsneade
 * @version $Id: $
 */
public class HomepageController extends ParameterizableViewController {
	private final Logger log = Logger.getLogger (this.getClass());

    private PostService postService;

    /**
     * <p>Setter for the field <code>postService</code>.</p>
     *
     * @param postService a {@link com.rnsolutions.stumblr.service.PostService} object.
     */
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /** {@inheritDoc}
     Loads up the first 10 posts and puts them in the model.
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	log.info("handleRequestInternal(): called...");
        final ModelAndView modelAndView = super.handleRequestInternal(request, response);

        //we really want 5, but we are checking to see if we need the "more posts" link
        final List<Post> posts = postService.getPosts(0, 6);

        modelAndView.addObject("postingList", posts);

        return modelAndView;
    }
    
}
