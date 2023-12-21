package com.example.crud3wings.models.entities;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository
        extends CrudRepository<Article, Integer> {
}
