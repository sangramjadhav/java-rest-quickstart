package org.sj.ws.rest.v2.service;

import org.sj.ws.rest.v2.entity.Post;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/blog")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/vnd.blog.v2+xml", "application/vnd.blog.v2+json"})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/vnd.blog.v2+xml", "application/vnd.blog.v2+json"})
public interface BloggingService {

    @GET
    @Path("/posts")
    List<Post> getPosts();

    @GET
    @Path("/post/{id}")
    Post getPost(@PathParam("id") int id);

    @POST
    @Path("/post")
    Response addPost(Post post);

    @PUT
    @Path("/post/{id}")
    Response updatePost(@PathParam("id") int id, Post post);

    @DELETE
    @Path("/post/{id}")
    Response deletePost(@PathParam("id") int id);
}