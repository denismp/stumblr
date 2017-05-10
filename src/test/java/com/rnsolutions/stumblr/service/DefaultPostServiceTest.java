package com.rnsolutions.stumblr.service;

import com.rnsolutions.stumblr.dao.PostDao;
import com.rnsolutions.stumblr.entity.Post;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author bsneade
 */
public class DefaultPostServiceTest {

    private PostDao postDao;
    private DefaultPostService postService;

    public DefaultPostServiceTest() {
    }

    @Before
    public void setUpClass() throws Exception {
        postDao = EasyMock.createMock(PostDao.class);

        postService = new DefaultPostService();
        postService.setPostDao(postDao);
    }

    /**
     * Test that the post dao is called.
     */
    @Test
    public void testSaveOrUpdate() {
        //reset the easy mock
        EasyMock.reset(postDao);

        //set up test data
        final Post post = new Post();

        //set up expectations
        postDao.saveOrUpdate(post);

        //replay the mocks
        EasyMock.replay(postDao);

        //call the method to be unit tested
        postService.saveOrUpdate(post);

        //verify the outcome
        EasyMock.verify(postDao);
    }

    /**
     * Test that the post dao is called.
     */
    @Test
    public void testDelete() {
        //reset the easy mock
        EasyMock.reset(postDao);

        //set up the test data
        final Post post = new Post();

        //set up expectations
        postDao.delete(post);

        //replat the mocks
        EasyMock.replay(postDao);

        //call the method to be unit tested
        postService.delete(post);

        //verify the outcome
        EasyMock.verify(postDao);
    }

    @Test
    public void testGetPosts_minValueAndMaxValue() {
        EasyMock.reset(postDao);

        final List<Post> posts = Arrays.asList(new Post[]{
                    new Post(), new Post(), new Post(), new Post()
                });

        EasyMock.expect(postDao.search(new HashMap<String, Object>())).andReturn(posts);

        EasyMock.replay(postDao);

        final List<Post> result = postService.getPosts(0, 4);

        EasyMock.verify(postDao);

        Assert.assertEquals(4, result.size());
    }

    @Test
    public void testGetPosts_minValueAndAboveMax() {
        EasyMock.reset(postDao);

        final List<Post> posts = Arrays.asList(new Post[]{
                    new Post(), new Post(), new Post(), new Post()
                });

        EasyMock.expect(postDao.search(new HashMap<String, Object>())).andReturn(posts);

        EasyMock.replay(postDao);

        final List<Post> result = postService.getPosts(0, 5);

        EasyMock.verify(postDao);

        Assert.assertEquals(4, result.size());
    }

    @Test
    public void testGetPosts_minValueAndBelowMax() {
        EasyMock.reset(postDao);

        final List<Post> posts = Arrays.asList(new Post[]{
                    new Post(), new Post(), new Post(), new Post()
                });

        EasyMock.expect(postDao.search(new HashMap<String, Object>())).andReturn(posts);

        EasyMock.replay(postDao);

        final List<Post> result = postService.getPosts(0, 2);

        EasyMock.verify(postDao);

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void testGetPosts_startAboveSize() {
        EasyMock.reset(postDao);

        final List<Post> posts = Arrays.asList(new Post[]{
                    new Post(), new Post(), new Post(), new Post()
                });

        EasyMock.expect(postDao.search(new HashMap<String, Object>())).andReturn(posts);

        EasyMock.replay(postDao);

        final List<Post> result = postService.getPosts(6, 7);

        EasyMock.verify(postDao);

        Assert.assertEquals(0, result.size());
    }
}
