package org.sj.ws.rest.service.impl;

import org.sj.ws.rest.model.BlogPost;
import org.sj.ws.rest.service.BloggingService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SampleBloggingService implements BloggingService {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private Map<Integer, BlogPost> posts;

    public SampleBloggingService() {
        posts = new ConcurrentHashMap<Integer, BlogPost>();
        posts.put(1, getHelloWorldPost(1));
    }

    @Override
    public List<BlogPost> getPosts() {
        return new ArrayList<BlogPost>(posts.values());
    }

    @Override
    public BlogPost getPost(int id) {
        return posts.get(id);
    }

    @Override
    public int addPost(BlogPost post) {
        int id = ID_GENERATOR.incrementAndGet();
        while (posts.containsKey(id)) {
            id = ID_GENERATOR.incrementAndGet();
        }
        post.setId(id);
        posts.put(id, post);
        return id;
    }

    @Override
    public boolean updatePost(int id, BlogPost post) {
        return posts.put(id, post) != null;
    }

    @Override
    public boolean deletePost(int id) {
        return posts.remove(id) != null;
    }

    private BlogPost getHelloWorldPost(int id) {
        BlogPost post = new BlogPost();
        post.setId(id);
        post.setCreateDate(new Date());
        post.setTags(Arrays.asList("first", "initial"));
        post.setCategories(Arrays.asList("Test"));
        post.setTitle("First Post");
        post.setContent("This is my first post.");
        return post;
    }
}
