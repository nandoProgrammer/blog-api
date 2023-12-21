package com.example.crud3wings.controllers;

import com.example.crud3wings.models.entities.Article;
import com.example.crud3wings.models.entities.ArticleRepository;
import com.example.crud3wings.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public Iterable<Article> allArticles() {
        return articleRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Article> findArticleById(@PathVariable int id) {
        return articleRepository.findById(id);
    }

    @PostMapping
    @RequestMapping
    public @ResponseBody Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @PutMapping(path = "/{id}")
    public Article updateArticle(@PathVariable long id, @RequestBody Article articleUpdate) {
        return articleRepository.findById(Math.toIntExact(id))
                .map(article -> {
                    articleUpdate.setId(article.getId());
                    return articleRepository.save(articleUpdate);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id " + id));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable int id) {
        articleRepository.deleteById(id);
    }
}
