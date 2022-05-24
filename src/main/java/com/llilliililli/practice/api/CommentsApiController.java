package com.llilliililli.practice.api;

import com.llilliililli.practice.dto.CommentsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CommentsApiController {

    @Autowired
    private CommentsService commentsService;

    // 댓글 목록 조회
    @GetMapping("api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentsDto>> comments (@PathVariable Long articleId){

        // 서비스에게 위임
        List<CommentsDto> dtos = commentsService.comments(articleId);

        // 결과응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentsDto> create(@PathVariable Long articleId, @RequestBody CommentsDto dto){

        // 서비스에게 위임
       CommentsDto createDto = commentsService.create(articleId,dto);

        // 결과응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }


    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentsDto> update(@PathVariable Long id, @RequestBody CommentsDto dto){

        // 서비스에게 위임
        CommentsDto updateDto = commentsService.update(id,dto);

        // 결과응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentsDto> delete(@PathVariable Long id){

        // 서비스에게 위임
        CommentsDto deleteDto = commentsService.delete(id);

        // 결과응답
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }

}
