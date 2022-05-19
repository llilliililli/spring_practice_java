package com.llilliililli.practice.api;

import com.llilliililli.practice.dto.ArticleForm;
import com.llilliililli.practice.entity.Article;
import com.llilliililli.practice.repository.ArticleRepository;
import com.llilliililli.practice.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Service를 활용한 Controller
// 웨이터 역할
// 주문 ( 요청 )을 받아서 메인 주방장에게 오더를 전달하고, 완성된 요리를 받는다.

@RestController // RestAPI용 컨트롤러 ( JSON 반환 )
@Slf4j // 로그사용
public class ArticleApiController2 {

    @Autowired //DI, 생성 객체를 가져와 연결!
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles2")
    public List<Article> index(){
        log.info("Service Get!");
        return articleService.index();
    }

    @GetMapping("/api/articles2/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles2")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return ( created != null ) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/articles2/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleForm dto, @PathVariable Long id){

        Article updated = articleService.update(id,dto);
        return ( updated != null ) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/articles2/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        Article deleted = articleService.delete(id);

        return ( deleted != null ) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){

        List<Article> createdList = articleService.createArticles(dtos);

        return ( createdList != null ) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
