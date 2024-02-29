package hello.config;

import hello.datasource.MyDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.util.List;

@Slf4j
@Configuration
public class MyDataSourceEnvConfig {
    private final Environment environment;

    public MyDataSourceEnvConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public MyDataSource myDataSource(){
        String url = environment.getProperty("my.datasource.url");
        String username = environment.getProperty("my.datasource.username");
        String password = environment.getProperty("my.datasource.password");
        int maxConnection = environment.getProperty("my.datasource.etc.max-connetion", Integer.class);
        Duration timeout = environment.getProperty("my.datasource.etc.timeout", Duration.class);
        List<String> property = environment.getProperty("my.datasource.etc.options", List.class);

        return new MyDataSource(url, username, password, maxConnection, timeout, property);
    }
}
