package com.llilliililli.practice.api;

import com.llilliililli.practice.dto.ArticleForm;
import com.llilliililli.practice.entity.Article;
import com.llilliililli.practice.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // RestAPI용 컨트롤러 ( JSON 반환 )
@Slf4j // 로그사용
public class ArticleApiController {

    @Autowired //DI
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleForm dto, @PathVariable Long id){

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {}, article : {}",id, article.toString());

        // 2. 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);


        // 3. 잘못된 요청 처리 ( 대상없거나, id가 다른 경우 )
        if(target == null || !id.equals(article.getId())){
            // 400 : 잘못된 요청 응답
            log.info("잘못된 요청! ==> id : {}, article : {}",id, article);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null); //400 처리!
        }

        // 4. 업데이트 및 정상 응답 ( 200 )
        target.patch(article); // 항목 누락되어도, null 방지
        Article updated = articleRepository.save(target);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if( target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 2. 대상 삭제
        articleRepository.delete(target);

        // 3. 데이터 반환
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
