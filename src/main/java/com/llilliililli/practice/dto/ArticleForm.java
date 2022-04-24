package com.llilliililli.practice.dto;

import com.llilliililli.practice.entity.Article;

public class ArticleForm {



    private String title;
    private String content;

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }



    // Entity 객체 생성
    public Article toEntity() {
        return new Article(null,title,content);
    }
}
