package com.llilliililli.practice.repository;

import com.llilliililli.practice.entity.Article;
import com.llilliililli.practice.entity.Comments;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest //JPA와 연동 테스트
class CommentsRepositoryTest {

    @Autowired CommentsRepository commentsRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {

        // Case1. 4번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(4L,"당신의 인생영화는?","댓글 ㄱ");

            Comments a = new Comments(1L,article,"Park","굿 윌 헌팅");
            Comments b = new Comments(2L,article,"Kim","아이 엠 샘");
            Comments c = new Comments(3L,article,"Choi","닥터스트레인지2");

            List<Comments> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "4번 댓글의 모든 댓글을 출력!");
        }


        // Case2. 1번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(1L,"가가가가가","1111111");
            List<Comments> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "1번 글은 댓글이 없음");
        }

        // Case3. 9번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 9L;

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByArticleId(articleId);

            // 예상하기
            List<Comments> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "9번 글은 댓글이 없음");
        }

        // Case4. 9999번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 9999L;

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByArticleId(articleId);

            // 예상하기
            List<Comments> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "9999번 글은 댓글이 없음");
        }

        // Case5. -1번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = -1L;

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByArticleId(articleId);

            // 예상하기
            List<Comments> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "-1번 글은 댓글이 없음");
        }


    }


    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {

        // Case1. "Park"의 모든 댓글 조회
        {
            // 입력 데이터 준비
            String nickname = "Park";

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByNickname(nickname);

            // 예상하기

            Comments a = new Comments(1L,new Article(4L,"당신의 인생영화는?","댓글 ㄱ"),nickname,"굿 윌 헌팅");
            Comments b = new Comments(4L,new Article(5L,"당신의 소울푸드는?","댓글 ㄱㄱ"),nickname,"치킨");
            Comments c = new Comments(7L,new Article(6L,"당신의 취미는?","댓글 ㄱㄱㄱ"),nickname,"조깅");

            List<Comments> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "닉네임이 Park인 모든 댓글을 출력!");
        }

        // Case2. "Kim"의 모든 댓글 조회
        {
            // 입력 데이터 준비
            String nickname = "Kim";

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByNickname(nickname);

            // 예상하기

            Comments a = new Comments(2L,new Article(4L,"당신의 인생영화는?","댓글 ㄱ"),nickname,"아이 엠 샘");
            Comments b = new Comments(5L,new Article(5L,"당신의 소울푸드는?","댓글 ㄱㄱ"),nickname,"샤브샤브");
            Comments c = new Comments(8L,new Article(6L,"당신의 취미는?","댓글 ㄱㄱㄱ"),nickname,"유튜브");

            List<Comments> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "닉네임이 Kim인 모든 댓글을 출력!");
        }

        // Case3. null 의 모든 댓글 조회
        {
            // 입력 데이터 준비
            String nickname = null;

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByNickname(nickname);

            // 예상하기
            List<Comments> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "닉네임이 null인 댓글은 없습니다.");
        }

        // Case4. ""의 모든 댓글 조회
        {
            // 입력 데이터 준비
            String nickname = "";

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByNickname(nickname);

            // 예상하기
            List<Comments> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "닉네임이 공백인 댓글은 없습니다.");
        }


        // Case5. "i"의 모든 댓글 조회
        {
            // 입력 데이터 준비
            String nickname = "i";

            // 실제 수행
            List<Comments> commentsList = commentsRepository.findByNickname(nickname);

            // 예상하기
            List<Comments> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),commentsList.toString(), "닉네임이 i인 댓글은 없습니다.");
        }

    }
}