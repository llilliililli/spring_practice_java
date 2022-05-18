package com.llilliililli.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity //DB가 해당 객체를 인식 가능 ( 해당 클래스로 DB 테이블을 만든다 )
@AllArgsConstructor // 생성자 롬복처리
@NoArgsConstructor // 디폴트 생성자 롬복처리
@ToString // ToString 롬복처리
@Getter // getter 롬복 처리
public class Article {

    @Id // 대표값을 지정! like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 1,2,3 .... 자동생성 어노테이션! ( 시퀀스 개념 )
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    // api Update 시, null인 항목 있다면, 기존에 있던 값으로 처리되도록 patch 함수 추가
    public void patch(Article article){
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }

    //디폴트 생성자 ( 파라미터 없는 생성자 )
//    Article(){
//
//    }


//롬복으로 인한 제거
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
