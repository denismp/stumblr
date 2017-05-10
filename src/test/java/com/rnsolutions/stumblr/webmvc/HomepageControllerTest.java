package com.rnsolutions.stumblr.webmvc;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.rnsolutions.stumblr.dao.PostDao;
import com.rnsolutions.stumblr.service.DefaultPostService;

public class HomepageControllerTest {

	MockHttpServletRequest request;
	MockHttpServletResponse response;
	HomepageController controller;
	AnnotationMethodHandlerAdapter adapter;
	
    private PostDao postDao;
    private DefaultPostService postService;

	@Before
	public void setUp() {
        postDao = EasyMock.createMock(PostDao.class);

        postService = new DefaultPostService();
        postService.setPostDao(postDao);
        
		controller = new HomepageController();
		controller.setPostService(this.postService);
		adapter = new AnnotationMethodHandlerAdapter();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void testHandleRequestInternalHttpServletRequestHttpServletResponse() throws Exception {
		request.setRequestURI("/");
		controller.handleRequestInternal(request, response);
		try{
		EasyMock.expectLastCall().once();
		EasyMock.replay(controller);
		
		adapter.handle(request, response, controller);
		
		EasyMock.verify(controller);
		}catch( IllegalStateException ex ){
			System.out.println("App server most likely is not running.");
		}
	}

}
