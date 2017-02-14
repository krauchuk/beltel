package by.grsu.service;

import by.grsu.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Post getById(long id);
    void delete(long id);
    Post save(Post post);
}
