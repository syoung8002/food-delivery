package fooddelivery.infra;

import fooddelivery.config.kafka.KafkaProcessor;
import fooddelivery.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventCollectorViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private EventCollectorRepository eventCollectorRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1(
        @Payload OrderPlaced orderPlaced
    ) {
        try {
            if (!orderPlaced.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(orderPlaced.getEventType());
            eventCollector.setCorrelationKey(
                String.valueOf(orderPlaced.getId())
            );
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(orderPlaced);
            eventCollector.setPayload(jsonPayload);
            eventCollector.setTimestamp(orderPlaced.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAccepted_then_CREATE_2(
        @Payload OrderAccepted orderAccepted
    ) {
        try {
            if (!orderAccepted.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(orderAccepted.getEventType());
            eventCollector.setCorrelationKey(String.valueOf(orderAccepted.getOrderId()));
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(orderAccepted);
            eventCollector.setPayload(jsonPayload);
            eventCollector.setTimestamp(orderAccepted.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderRejected_then_CREATE_3(
        @Payload OrderRejected orderRejected
    ) {
        try {
            if (!orderRejected.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(orderRejected.getEventType());
            eventCollector.setCorrelationKey(String.valueOf(orderRejected.getOrderId()));
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(orderRejected);
            eventCollector.setPayload(jsonPayload);
            eventCollector.setTimestamp(orderRejected.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookStarted_then_CREATE_4(
        @Payload CookStarted cookStarted
    ) {
        try {
            if (!cookStarted.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(cookStarted.getEventType());
            eventCollector.setCorrelationKey(String.valueOf(cookStarted.getOrderId()));
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(cookStarted);
            eventCollector.setPayload(jsonPayload);
            eventCollector.setTimestamp(cookStarted.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookFinished_then_CREATE_5(
        @Payload CookFinished cookFinished
    ) {
        try {
            if (!cookFinished.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(cookFinished.getEventType());
            eventCollector.setCorrelationKey(String.valueOf(cookFinished.getOrderId()));
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(cookFinished);
            eventCollector.setPayload(jsonPayload);
            eventCollector.setTimestamp(cookFinished.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_CREATE_6(
        @Payload DeliveryStarted deliveryStarted
    ) {
        try {
            if (!deliveryStarted.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(deliveryStarted.getEventType());
            eventCollector.setCorrelationKey(String.valueOf(deliveryStarted.getOrderId()));
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(deliveryStarted);
            eventCollector.setPayload(jsonPayload);
            eventCollector.setTimestamp(deliveryStarted.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryCompleted_then_CREATE_7(
        @Payload DeliveryCompleted deliveryCompleted
    ) {
        try {
            if (!deliveryCompleted.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(deliveryCompleted.getEventType());
            eventCollector.setCorrelationKey(String.valueOf(deliveryCompleted.getOrderId()));
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(deliveryCompleted);
            eventCollector.setPayload(jsonPayload);
            eventCollector.setTimestamp(deliveryCompleted.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
