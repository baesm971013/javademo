package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("all benas")
    void findAllBean(){
        String[] bdef = ac.getBeanDefinitionNames();
        for (String bName : bdef){
            Object bean = ac.getBean(bName);
            System.out.println(bName + "object: " + bean);
        }
    }
    @Test
    @DisplayName("application beans")
    void findAppBean(){
        String[] bdef = ac.getBeanDefinitionNames();
        for (String bName : bdef){
            BeanDefinition beanDefinition = ac.getBeanDefinition(bName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(bName);
                System.out.println(bName + "object: " + bean);
            }
        }
    }


}
