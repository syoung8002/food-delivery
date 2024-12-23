package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
public class DeliveryCompleted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String userId;
    private String receiverAddr;
    private String riderId;
    private String status;
}
//>>> DDD / Domain Event
