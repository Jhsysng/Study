package hello.selector;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class ImportSelectorTest {
    @Test
    void statticConfig(){
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(StaticConfig.class);
        HelloBean helloBean = appContext.getBean(HelloBean.class);
        Assertions.assertThat(helloBean).isNotNull();

    }

    @Test
    void seletorConfig() {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(SelectorConfig.class);
        HelloBean helloBean = appContext.getBean(HelloBean.class);
        Assertions.assertThat(helloBean).isNotNull();
    }

    @Configuration
    @Import(HelloImportSelector.class)
    public static class SelectorConfig {
    }

    @Configuration
    @Import(HelloConfig.class)
    public static class StaticConfig {
    }
}
