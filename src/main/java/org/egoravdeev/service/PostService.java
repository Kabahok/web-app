package org.egoravdeev.service;

import org.egoravdeev.exeption.NotFoundExeption;
import org.egoravdeev.model.Post;
import org.egoravdeev.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        if (repository.getById(id).isEmpty()) {
            throw new NotFoundExeption("Пост не найден");
        } else {
            return repository.getById(id).get();
        }
    }

    public Post save(Post post) throws NotFoundExeption {
        return repository.save(post);
    }

    public void removeById(long id) throws NotFoundExeption {
        repository.removeById(id);
    }

}
