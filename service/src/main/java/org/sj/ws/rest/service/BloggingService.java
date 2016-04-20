package org.sj.ws.rest.service;

import org.sj.ws.rest.model.BlogPost;
import java.util.List;

/**
 * Blogging Service interface
 */
public interface BloggingService {

    List<BlogPost> getPosts();

    BlogPost getPost(int id);

    int addPost(BlogPost post);

    boolean updatePost(int id, BlogPost post);

    boolean deletePost(int id);
}
