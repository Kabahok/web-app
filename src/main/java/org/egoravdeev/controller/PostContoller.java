package org.egoravdeev.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.egoravdeev.exeption.NotFoundExeption;
import org.egoravdeev.model.Post;
import org.egoravdeev.service.PostService;

import java.io.IOException;
import java.io.Reader;

public class PostContoller {
    private static final String APLICATION_JSON = "application_json";
    private final PostService service;

    public PostContoller(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse res) throws IOException {
        res.setContentType(APLICATION_JSON);
        final var gson = new Gson();
        res.getWriter().print(gson.toJson(service.all()));
    }

    public void getById(long id, HttpServletResponse res) throws IOException {
        final var gson = new Gson();
        try {
            final var post = service.getById(id);
            res.setContentType(APLICATION_JSON);
            res.getWriter().print(gson.toJson(post));
        } catch (NotFoundExeption e) {
            res.getWriter().print(e.getMessage());
        }
    }

    public void removeById(long id, HttpServletResponse res) throws IOException {
        try {
            service.removeById(id);
            res.getWriter().print("Пост успешно удален");
        } catch (NotFoundExeption e) {
            res.setContentType("text/plain");
            res.getWriter().print(e.getMessage());
        }
    }

    public void save(Reader body, HttpServletResponse res) throws IOException {
        res.setContentType(APLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        res.getWriter().print(gson.toJson(service.save(post)));
    }
}
