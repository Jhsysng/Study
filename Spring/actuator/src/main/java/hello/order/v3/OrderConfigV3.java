package hello.order.v3;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV3 {
    @Bean
    OrderServiceV3 orderService(MeterRegistry registry) {
        return new OrderServiceV3(registry);
    }
}
