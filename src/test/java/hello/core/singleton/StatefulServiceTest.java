package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void t1(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService s1 = ac.getBean(StatefulService.class);
        StatefulService s2 = ac.getBean(StatefulService.class);

        int p1 = s1.order("userA",10000);

        int p2 = s2.order("userb",20000);
        Assertions.assertThat(p1).isEqualTo(10000);


    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}