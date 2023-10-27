package org.egoravdeev.repository;

import org.egoravdeev.constants.StringConsts;
import org.egoravdeev.exeption.NotFoundExeption;
import org.egoravdeev.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class PostRepository {
    private ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
    private AtomicLong countId = new AtomicLong(0);

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        if (posts.containsKey(id)) return Optional.of(posts.get(id));
        return Optional.empty();
    }

    public Post save(Post post) throws NotFoundExeption {
        if (post.getId() == 0) {
            long id = countId.incrementAndGet();
            Post item = new Post(id, post.getContent());
            posts.put(id, item);
            return item;
        } else {
            if (posts.containsKey(post.getId())) {
                posts.get(post.getId()).setContent(post.getContent());
                return posts.get(post.getId());
            }
        }

        throw new NotFoundExeption(StringConsts.NOT_FOUND_POST_WITH_ID);
    }

    public void removeById(long id) {
        if (posts.containsKey(id)) {
            posts.remove(id);
        } else {
            throw new NotFoundExeption(StringConsts.NOT_DELETE_POST);
        }
    }


}
