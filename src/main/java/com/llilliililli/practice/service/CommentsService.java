package com.llilliililli.practice.service;

import com.llilliililli.practice.dto.CommentsDto;
import com.llilliililli.practice.entity.Article;
import com.llilliililli.practice.entity.Comments;
import com.llilliililli.practice.repository.ArticleRepository;
import com.llilliililli.practice.repository.CommentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentsService{

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentsDto> comments(Long articleId) {

        // 조회 : 댓글 목록
        List<Comments> comments = commentsRepository.findByArticleId(articleId);

        // 변환 : 엔티티 -> DTO
        List<CommentsDto> dtos = new ArrayList<>();

//        for (int i =0; i< comments.size(); i++){
//            Comments c = comments.get(i);
//            CommentsDto dto = CommentsDto.createCommentDto(c);
//            dtos.add(dto);
//        }

        // 반환
        //return dtos;

        // for문 스트림 문법으로 개선
        return commentsRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentsDto.createCommentDto(comment))
                .collect(Collectors.toList());


    }

    @Transactional
    public CommentsDto create(Long articleId, CommentsDto dto) {

        // 로그 확인으로 일일이 넣기엔 불편한 소스
//        log.info("입력값 => {}",articleId);
//        log.info("입력값 => {}",dto);

        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        // 댓글 엔티티 생성
        Comments comment = Comments.createComment(dto,article);

        // 댓글 엔티티를 DB로 저장
        Comments created = commentsRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentsDto.createCommentDto(created);

        // 로그 확인으로 일일이 넣기엔 불편한 소스
//        CommentsDto createDto = CommentsDto.createCommentDto(created);
//        log.info("반환값 => {}",createDto);

//        return createDto;
    }

    @Transactional
    public CommentsDto update(Long id, CommentsDto dto) {

        // 댓글 조회 및 예외 발생
        Comments target = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB로  갱신
        Comments updated = commentsRepository.save(target);

        //댓글 엔티티를 DTO로 변환 및 반환
        return CommentsDto.createCommentDto(updated);
    }


    @Transactional
    public CommentsDto delete(Long id) {

        // 댓글 조회 및 예외 발생
        Comments target = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 없습니다."));

        // 댓글 DB에서 삭제
        commentsRepository.delete(target);

        // 삭제 댓글 DTO로 반환
        return CommentsDto.createCommentDto(target);
    }
}
