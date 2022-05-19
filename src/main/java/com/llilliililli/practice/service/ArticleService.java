package com.llilliililli.practice.service;

import com.llilliililli.practice.dto.ArticleForm;
import com.llilliililli.practice.entity.Article;
import com.llilliililli.practice.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

// 메인 셰프 역할 ( 주방장 )
// 웨이터 ( 컨트롤러 )에게 오더를 받고, 보조 주방장 ( 리파짓토리 )에게 데이터를 받아서, 요리 완성 후, 웨이터에게 전달!
@Slf4j
@Service //서비스 선언! ( 서비스 객체를 스프링 부트에 선언 )
public class ArticleService {

    //보조 주방장
    // 메인 셰프에게 요리에 필요한 재료를 전달해준다.
    @Autowired // DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {}, article : {}",id, article.toString());

        // 2. 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);


        // 3. 잘못된 요청 처리 ( 대상없거나, id가 다른 경우 )
        if(target == null || !id.equals(article.getId())){
            // 400 : 잘못된 요청 응답
            log.info("잘못된 요청! ==> id : {}, article : {}",id, article);
            return null;
        }

        // 4. 업데이트 및 정상 응답 ( 200 )
        target.patch(article); // 항목 누락되어도, null 방지
        Article updated = articleRepository.save(target);

        return updated;
    }

    public Article delete(Long id) {

        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if( target == null){
            return null;
        }

        // 2. 대상 삭제
        articleRepository.delete(target);

        // 3. 데이터 반환
        return target;
    }

    @Transactional // 해당 메소드를 트랜잭션으로 묶는다! ( 예외 시, 자동롤백 )
    public List<Article> createArticles(List<ArticleForm> dtos) {

        // 1. dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList()); // stream 문법

        // 2. entity 묶음을 DB로 저장
        articleList.stream().forEach(article -> articleRepository.save(article)); // stream 문법

        // 3. 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결재 실패!"));

        // 4. 결과값 반환
        return articleList;
    }
}
