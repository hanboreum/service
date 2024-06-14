package org.delivery.storeadmin.domain.userorder.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.storeadmin.domain.userorder.business.UserOrderBusiness;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

//사용자 주문 받기
@Component
@RequiredArgsConstructor
@Slf4j
public class UserOrderConsumer {

    private final UserOrderBusiness userOrderBusiness;

    @RabbitListener(queues = "delivery.queue")//delivery.queue 로부터 받아온다
    public void userOrderConsumer(
        UserOrderMessage userOrderMessage
    ) {

        log.info("message queue >> : {}", userOrderMessage);
        userOrderBusiness.pushUserOrder(userOrderMessage);
    }
}
