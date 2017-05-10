/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rnsolutions.stumblr.webmvc;

import com.rnsolutions.stumblr.entity.LinkPost;
import com.rnsolutions.stumblr.entity.TextPost;
import com.rnsolutions.stumblr.service.PostService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * <p>NewTextPostController class.</p>
 *
 * @author bsneade
 * @version $Id: $
 */
public class NewLinkPostController extends ParameterizableViewController {
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

    /** {@inheritDoc} */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	log.info("handleRequestInternal(): called...");
        if (!StringUtils.isBlank(request.getParameter("link"))) {
            //save the new post
            final LinkPost linkPost = new LinkPost();
            log.info("Parameter link=" + request.getParameter("link"));
            log.info("Parameter title=" + request.getParameter("title"));
            linkPost.setLink(request.getParameter("link"));
            linkPost.setTitle(request.getParameter("title"));
            postService.saveOrUpdate(linkPost);
            return new ModelAndView("redirect:/homepage.blr");
        }
        return super.handleRequestInternal(request, response);
    }
    
}
