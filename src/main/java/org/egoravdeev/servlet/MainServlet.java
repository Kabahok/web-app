package org.egoravdeev.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.egoravdeev.constants.StringConsts;
import org.egoravdeev.controller.PostContoller;
import org.egoravdeev.repository.PostRepository;
import org.egoravdeev.service.PostService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private PostContoller controller;
    @Override
    public void init() throws ServletException {
        final var context = new AnnotationConfigApplicationContext("org.egoravdeev");
        controller = context.getBean(PostContoller.class);
    }

    

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();

            if (method.equals(StringConsts.METHOD_GET) && path.equals(StringConsts.API_POSTS)) {
                controller.all(resp);
                return;
            } else if (method.equals(StringConsts.METHOD_GET) && path.matches(StringConsts.API_POSTS_ID)) {
                final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                controller.getById(id, resp);
                return;
            } else if (method.equals(StringConsts.METHOD_POST) && path.equals(StringConsts.API_POSTS)) {
                controller.save(req.getReader(), resp);
                return;
            } else if (method.equals(StringConsts.METHOD_DELETE) && path.matches(StringConsts.API_POSTS_ID)) {
                final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                controller.removeById(id, resp);
                return;
            }

            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
