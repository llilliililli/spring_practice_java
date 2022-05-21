package com.llilliililli.practice.service;

import com.llilliililli.practice.dto.ArticleForm;
import com.llilliililli.practice.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //해당 클래스는 스프링부트와 연동되어 테스팅 된다.
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    // ArticleService index 테스트
    @Test
    void index() {
        // 예상
        Article a = new Article(1L,"가가가가가","1111111");
        Article b = new Article(2L,"나나나나나","2222222");
        Article c = new Article(3L,"다다다다다","3333333"); // 성공 케이스
        //Article c = new Article(4L,"다다다다다","3333333"); //실패 케이스

        List<Article> expected = new ArrayList<>(Arrays.asList(a,b,c));

        //실제
        List<Article> articles = articleService.index();

        //비교
        assertEquals(expected.toString(),articles.toString());
    }

    // ArticleService show 테스트
    @Test
    void show_success() {
        //존재하는 아이디 입력 케이스

        // 예상
        Long id = 1L;
        Article expected = new Article(id,"가가가가가","1111111"); //성공케이스
        //Article expected = new Article(id,"가가가가가1231","1111111"); //실패케이스

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(),article.toString());

    }


    @Test
    void show_fail() {
        // 존재하지 않는 아이디 입력 케이스

        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected,article); // null toString 불가

    }

    // ArticleService create 테스트
    @Test
    @Transactional // 데이터 생성, 변경, 삭제 케이스는 롤백을 위한 트랜잭션 처리!
    void create_success() {
        //title과 content만 있는 DTO 케이스

        // 예상
        String title = "라라라라라라";
        String content = "444444";
        ArticleForm dto = new ArticleForm(title,content,null);

        Article expected = new Article(4L,title,content); // 성공케이스
        //Article expected = new Article(5L,title,content); // 실패케이스


        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(),article.toString());

    }

    @Test
    @Transactional // 데이터 생성, 변경, 삭제 케이스는 롤백을 위한 트랜잭션 처리!
    void create_fail() {
        // 아이디가 포함된 dto가 입력된 케이스


        // 예상
        String title = "라라라라라라";
        String content = "444444";
        ArticleForm dto = new ArticleForm(title,content,4L);

        Article expected = null;


        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected,article);

    }


    // ArticleService update 테스트
    @Test
    @Transactional
    void update_success() {
        // 존재하는 id와 title, content가 있는 dto 입력 케이스

        // 예상
        Long id = 1L;
        String title = "AAAAAA";
        String content = "888888";
        ArticleForm dto = new ArticleForm(title,content,id);

        Article expected = new Article(id,title,content); // 성공케이스
        //Article expected = new Article(5L,title,content); // 실패케이스


        // 실제
        Article article = articleService.update(id,dto);

        // 비교
        assertEquals(expected.toString(),article.toString());

    }

    @Test
    @Transactional
    void update_success2() {
        // 존재하는 id와 title만 있는 dto 입력 케이스

        // 예상
        Long id = 1L;
        String title = "AAAAAAA";
        String content = "1111111"; // content 값은 그대로!
        ArticleForm dto = new ArticleForm(title,content,id);

        Article expected = new Article(id,title,content); // 성공케이스
        //Article expected = new Article(5L,title,content); // 실패케이스


        // 실제
        Article article = articleService.update(id,dto);

        // 비교
        assertEquals(expected.toString(),article.toString());

    }

    @Test
    @Transactional
    void update_fail() {
        // 존재하지않는 id와 dto 입력 케이스

        // 예상
        Long id = 4L;
        String title = "라라라라라라";
        String content = "444444";
        ArticleForm dto = new ArticleForm(title,content,id);

        Article expected = null;


        // 실제
        Article article = articleService.update(id,dto);

        // 비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void update_fail2() {
        // id만 있는 dto 입력 케이스

        // 예상
        Long id = 4L;
        String title = null;
        String content = null;
        ArticleForm dto = new ArticleForm(title,content,id);

        Article expected = null;


        // 실제
        Article article = articleService.update(id,dto);

        // 비교
        assertEquals(expected,article);
    }


    // ArticleService delete 테스트
    @Test
    @Transactional
    void delete_success() {
        // 존재하는 id 입력 케이스

        // 예상
        Long id = 1L;
        String title = "가가가가가";
        String content = "1111111";

        Article expected = new Article(id,title,content); // 성공케이스
        //Article expected = new Article(5L,title,content); // 실패케이스


        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void delete_fail() {
        // 존재하지않는 id 입력 케이스

        // 예상
        Long id = 5L;
        String title = "가가가가가";
        String content = "1111111";

        Article expected = null;


        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected,article);

    }
}