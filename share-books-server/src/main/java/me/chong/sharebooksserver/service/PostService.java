package me.chong.sharebooksserver.service;

import me.chong.sharebooksserver.domain.PostRepository;
import me.chong.sharebooksserver.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post save(Post post) {
        return repository.save(post);
    }

    public List<Post> findAll() {
        return repository.findAll();
    }
}
