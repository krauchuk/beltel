package by.grsu.service.Impl;

import by.grsu.entity.Post;
import by.grsu.repository.PostRepository;
import by.grsu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository service;

    public List<Post> getAll() {
        return service.findAll();
    }

    public Post getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public Post save(Post post) {
        return service.saveAndFlush(post);
    }
}
