package com.llilliililli.practice.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //DB가 해당 객체를 인식 가능
@AllArgsConstructor // 생성자 롬복처리
@ToString // ToString 롬복처리
public class Article {

    @Id // 대표값을 지정! like a 주민등록번호
    @GeneratedValue // 1,2,3 .... 자동생성 어노테이션! ( 시퀀스 개념 )
    private Long id;

    @Column
    private String title;

    @Column
    private String content;



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
