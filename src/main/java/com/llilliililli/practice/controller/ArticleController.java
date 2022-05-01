package com.llilliililli.practice.controller;

import com.llilliililli.practice.dto.ArticleForm;
import com.llilliililli.practice.entity.Article;
import com.llilliililli.practice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired // 스프링 부트가 미리생성 해놓은 객체를 가져다가 자동 연결! //-> 오류 해결 필요
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

        //form 데이터 확인
        System.out.println(form.toString());

        // 1. DTO를 변환! Entity!
        Article article = form.toEntity();
        System.out.println(article.toString());


        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        System.out.println(saved);

        return "";
    }
}
