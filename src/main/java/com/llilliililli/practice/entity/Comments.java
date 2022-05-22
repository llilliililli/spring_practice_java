package com.llilliililli.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


//댓글 엔티티 생성
@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 다 대 일 ( 해당 엔티티 여러개가 하나의 Article에 연관된다. )
    @JoinColumn(name = "article_id") // FK 컬럼 설정 ( article_id 컬럼에 Article의 대표( PK ) 컬럼 ( id ) 설정 )
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

}
