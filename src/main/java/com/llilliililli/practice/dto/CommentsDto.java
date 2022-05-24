package com.llilliililli.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.llilliililli.practice.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentsDto {

    private Long id;
    @JsonProperty("article_id") // JSON으로 넘어오는 형식으로 자동 매핑
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentsDto createCommentDto(Comments comment) {

        return new CommentsDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
