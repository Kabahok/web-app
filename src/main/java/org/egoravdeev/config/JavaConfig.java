package org.egoravdeev.config;


import org.egoravdeev.controller.PostContoller;
import org.egoravdeev.repository.PostRepository;
import org.egoravdeev.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    PostContoller postContoller(PostService service) {
        return new PostContoller(service);
    }

    @Bean
    PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    PostRepository postRepository() {
        return new PostRepository();
    }

}
