package com.llilliililli.practice.controller;

import com.llilliililli.practice.service.CommentsService;
import com.llilliililli.practice.dto.ArticleForm;
import com.llilliililli.practice.dto.CommentsDto;
import com.llilliililli.practice.entity.Article;
import com.llilliililli.practice.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리생성 해놓은 객체를 가져다가 자동 연결! //-> 오류 해결 필요
    private ArticleRepository articleRepository;

    @Autowired
    private CommentsService commentsService;

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
        List<CommentsDto> commentsDtos = commentsService.comments(id); // 댓글 리스트 가져오기


        // 2. 가져온 데이터를 모델에 등록!
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentsDtos",commentsDtos); // 댓글 리스트 모델 등록

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
        return "articles/index"; // articles/index.mustache
    }

    // @PathVariable -> url에 있는 변수 값 활용 { article.id } -> Long id
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        // 수정할 데이터 가져오기!
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //모델에 데이터를 등록!
        model.addAttribute("article",articleEntity);

        // 뷰 페이지를 설정
        return "articles/edit"; // articles/edit.mustache
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        log.info(form.toString());

        // 1. DTO를 엔티티로 변환한다.

        Article articleEntity = form.toEntity();

        log.info(articleEntity.toString());

        // 2. 엔티티를 DB로 저장한다.
        // 2-1. DB에서 기존 데이터를 가져온다.
         Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터의 값을 갱신한다.
        if( target != null){
            articleRepository.save(articleEntity); // 엔티티가 DB로 갱신
        }

        // 신. 수정 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles/"+articleEntity.getId();
    }


    //@DeleteMapping("/articles/{id}/delete") //Html 지원X -> 추후 사용예정
    // RedirectAttributes : redirect에서 사용하기위한 객체
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!");

        // 1. 삭제 대상을 가져온다!
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 대상을 삭제한다.
        if(target != null){
            log.info(target.toString());
            articleRepository.delete(target);
            // 1회성 attribute 데이터 ( addFlashAttribute )
            // header.mustache에 해당 내용 전송
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다!");
        }

        // 3. 결과 페이지로 리다이렉트한다.
        return "redirect:/articles";
    }
}
