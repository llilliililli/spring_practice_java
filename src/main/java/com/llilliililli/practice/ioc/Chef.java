package com.llilliililli.practice.ioc;

import org.springframework.stereotype.Component;

@Component //  Component :  해당 클래스를 객체로 만들고, 이를 IoC 컨테이너에 등록!
public class Chef {

    //Chef는 IngredientFactory를 알고 있음
    private IngredientFactory ingredientFactory;

    //Chef가 IngredientFactory 협업하기 위한 DI
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {

        //재료준비
        //Pork pork = new Pork("한돈 등심");
        //Beef beef = new Beef("한우 꽃등심");

        //DI : 외부에서 값을 삽입 받는 방식 사용! ( 코드를 유연하게 작성 가능 )
        Ingredient ingredient = ingredientFactory.get(menu);


        //요리반환
        return ingredient.getName()+"으로 만든 "+menu;
    }
}
