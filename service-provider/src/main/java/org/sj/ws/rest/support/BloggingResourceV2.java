package org.sj.ws.rest.support;

import org.sj.ws.rest.v2.entity.Post;
import org.sj.ws.rest.v2.service.BloggingService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.dozer.Mapper;
import org.sj.ws.rest.model.BlogPost;

public class BloggingResourceV2 implements BloggingService {

    private org.sj.ws.rest.service.BloggingService service;
    private Mapper mapper;

    public BloggingResourceV2(org.sj.ws.rest.service.BloggingService service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<Post> getPosts() {
        List<BlogPost> posts = service.getAllPosts();
        List<Post> dtoPosts = new ArrayList<Post>(posts.size());
        for (BlogPost blogPost : posts) {
            dtoPosts.add(mapper.map(blogPost, Post.class));
        }
        return dtoPosts;
    }

    @Override
    public Post getPost(int id) {
        BlogPost post = service.getPost(id);
        if (post == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return mapper.map(post, Post.class);
    }

    @Override
    public Response addPost(Post post) {
        int id = service.addPost(mapper.map(post, BlogPost.class));
        return Response.created(UriBuilder.fromPath(Integer.toString(id)).build()).build();
    }

    @Override
    public Response updatePost(int id, Post post) {
        boolean success = service.updatePost(id, mapper.map(post, BlogPost.class));
        if (!success) {
            throw new WebApplicationException(Response.Status.NOT_MODIFIED);
        }
        return Response.ok().build();
    }

    @Override
    public Response deletePost(int id) {
        boolean success = service.deletePost(id);
        if (!success) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok().build();
    }
}