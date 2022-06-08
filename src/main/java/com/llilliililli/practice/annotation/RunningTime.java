package com.llilliililli.practice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 실행시간 측정용 어노테이션!
//궁금하면 구글링 Target, Retention
@Target({ ElementType.TYPE,ElementType.METHOD}) // 어노테이션 적용 대상
@Retention(RetentionPolicy.RUNTIME) //어노테이션 유지 기간
public @interface RunningTime {
}
