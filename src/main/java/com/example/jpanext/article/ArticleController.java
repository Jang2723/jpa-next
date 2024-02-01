package com.example.jpanext.article;

import com.example.jpanext.article.entity.Article;
import com.example.jpanext.article.repo.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping("/test")
    public String test() {
        articleRepository.save(Article.builder()
                    .title("test")
                    .content("test")
                    .writer("test")
                    .build());
        return "done";
    }

}
