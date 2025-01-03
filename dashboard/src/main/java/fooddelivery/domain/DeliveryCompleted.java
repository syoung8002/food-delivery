package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeliveryCompleted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String userId;
    private String receiverAddr;
    private String riderId;
    private String status;
}
