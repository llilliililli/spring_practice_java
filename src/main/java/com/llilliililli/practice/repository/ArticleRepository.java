package com.llilliililli.practice.repository;

import com.llilliililli.practice.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


// < entity, entity의 대표값타입 >
public interface ArticleRepository extends CrudRepository<Article,Long> {

    @Override
    ArrayList<Article> findAll();
}
