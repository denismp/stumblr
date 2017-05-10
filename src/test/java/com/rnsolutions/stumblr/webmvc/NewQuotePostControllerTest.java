package com.rnsolutions.stumblr.webmvc;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.rnsolutions.stumblr.dao.PostDao;
import com.rnsolutions.stumblr.service.DefaultPostService;

public class NewQuotePostControllerTest {
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	NewQuotePostController controller;
	AnnotationMethodHandlerAdapter adapter;
	
    private PostDao postDao;
    private DefaultPostService postService;
    
	@Before
	public void setUp() {
        postDao = EasyMock.createMock(PostDao.class);

        postService = new DefaultPostService();
        postService.setPostDao(postDao);
        
		controller = new NewQuotePostController();
		controller.setPostService(this.postService);
		adapter = new AnnotationMethodHandlerAdapter();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	@Test
	public void testHandleRequestInternalHttpServletRequestHttpServletResponse() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/new/quote.blr");
		request.addParameter("quote", "my quote");
		request.addParameter("source", "my source");
		controller.handleRequestInternal(request, response);

	}

}
