package com.llilliililli.practice.repository;

import com.llilliililli.practice.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Comments Repository
// JpaRepository -> Paging 및 Sorting 처리가 가능한 Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {

    // 특정 게시글의 모든 댓글 조회
    // 쿼리 어노테이션 형식
    @Query(value =
            "SELECT * FROM COMMENTS WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comments> findByArticleId(@Param("articleId") Long articleId); // @Param 필요


    // 특정 닉네임의 모든 댓글 조회
    // xml 형식
    List<Comments> findByNickname(@Param("nickname") String nickname); // @Param 필요
}
