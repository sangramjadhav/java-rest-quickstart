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
import java.util.stream.Collectors;

public class SampleBloggingService implements BloggingService {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private Map<Integer, BlogPost> posts;

    public SampleBloggingService() {
        this.posts = new ConcurrentHashMap<Integer, BlogPost>();
        this.posts.put(1, createSamplePost(1));
    }

    @Override
    public List<BlogPost> getAllPosts() {

        return new ArrayList<BlogPost>(this.posts.values());
    }

    @Override
    public List<BlogPost> getPosts(int limit) {
        return this.posts.values().stream().sorted().collect(Collectors.toList()).subList(0, limit);
    }

    @Override
    public BlogPost getPost(int id) {
        return this.posts.get(id);
    }

    @Override
    public int addPost(BlogPost post) {
        int id = ID_GENERATOR.incrementAndGet();
        while (this.posts.containsKey(id)) {
            id = ID_GENERATOR.incrementAndGet();
        }
        post.setId(id);
        this.posts.put(id, post);
        return id;
    }

    @Override
    public boolean updatePost(int id, BlogPost post) {
        return this.posts.put(id, post) != null;
    }

    @Override
    public boolean deletePost(int id) {
        return this.posts.remove(id) != null;
    }

    private BlogPost createSamplePost(int id) {
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
