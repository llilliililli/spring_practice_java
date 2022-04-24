package com.llilliililli.practice.repository;

import com.llilliililli.practice.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// < entity, entity의 대표값타입 >
public interface ArticleRepository extends CrudRepository<Article,Long> {
}
