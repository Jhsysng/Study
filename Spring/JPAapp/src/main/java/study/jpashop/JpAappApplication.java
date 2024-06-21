package study.jpashop;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpAappApplication {


    public static void main(String[] args) {
        SpringApplication.run(JpAappApplication.class, args);
    }

    @Bean
    Hibernate6Module hibernate6Module() {
        return new Hibernate6Module();
    }

}
