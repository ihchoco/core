package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자 10000원 주문
        //statefulService1.order("userA", 10000);
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadB : B사용자 20000원 주문
        //statefulService2.order("userB", 20000);
        int userBPrice = statefulService1.order("userB", 20000);


        //A가 주문하고 금액을 조회하는 사이에 B가 들어와서 주문을 하는 경우

        //당연히 10000원이 나오게 기대하지만 실제로 결과는 20000원이 나온다

        //TrheadA : 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        int price = userAPrice;
        System.out.println("price = "+price);

        Assertions.assertThat(price).isEqualTo(10000);

    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}