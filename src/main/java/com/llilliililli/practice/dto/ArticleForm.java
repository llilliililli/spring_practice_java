package com.llilliililli.practice.dto;

import com.llilliililli.practice.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor // 생성자 롬복처리
@ToString // ToString 롬복처리
@Setter // 데이터 생성을 위한 setter ( api Post 이슈 )
@NoArgsConstructor // api Post 이슈
public class ArticleForm {



    private String title;
    private String content;
    private Long id;


    // Entity 객체 생성
    public Article toEntity() {
        return new Article(id,title,content);
    }

//롬복으로 인한 제거
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }


//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }



}
