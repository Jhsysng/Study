package hello.pay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final PayClient payClient;
    public void order(int money){
        log.info("주문 금액={}", money);
        payClient.pay(money);
    }

}
