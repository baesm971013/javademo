package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

    @Test
    void t1(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () ->ac.getBean(DiscountPolicy.class));
    }

    @Test
    void t2(){
        DiscountPolicy d1 = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(d1).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("Not good case specific injection")
    void t3(){
        DiscountPolicy d2 = ac.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(d2).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    void t4(){
        Map<String,Object> btype = ac.getBeansOfType(Object.class);
        for(String key : btype.keySet()){
            System.out.println(key + btype.get(key));
        }
    }



}
