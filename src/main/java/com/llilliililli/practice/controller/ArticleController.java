package com.llilliililli.practice.controller;

import com.llilliililli.practice.dto.ArticleForm;
import com.llilliililli.practice.entity.Article;
import com.llilliililli.practice.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller
@Slf4j // 로깅을 위한 어노테이션
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
        //System.out.println(form.toString()); -> 로깅 기능으로 대체!
        log.info(form.toString());

        // 1. DTO를 변환! Entity!
        Article article = form.toEntity();
        //System.out.println(article.toString()); -> 로깅 기능으로 대체!
        log.info(article.toString());


        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        //System.out.println(saved); -> 로깅 기능으로 대체!
        log.info(saved.toString());

        //redirect 적용 ( 생성한 id의 상세 내용 페이지로 이동 )
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}") // 데이터 조회를 위해, 데이터 id 값으로 조회
    public String show(@PathVariable Long id, Model model){
        log.info("id :: "+id);

        // 1. id로 데이터를 가져옴!
        //Optional<Article> articleEntity = articleRepository.findById(id);

        Article articleEntity = articleRepository.findById(id).orElse(null);


        // 2. 가져온 데이터를 모델에 등록!
        model.addAttribute("article",articleEntity);

        // 3. 보여줄 페이지를 설정!
        return "articles/show";

    }


    // List Entity View에 뿌려주기
    @GetMapping("/articles")
    public String index(Model model){

        // 1. 모든 Article을 가져온다!

        //방법 1
        // repository findAll 오버라이드를 통해서, List 캐스팅 필요 없어짐
        List<Article> articleEntityList = articleRepository.findAll(); // (List<Article>) -> X

        //방법 2
         //Iterable<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 Article 묶음을 뷰로 전달!
        model.addAttribute("articleList",articleEntityList);

        // 3. 뷰 페이지를 설정 !
        return "articles/index"; //articles/index.mustache
    }
}
