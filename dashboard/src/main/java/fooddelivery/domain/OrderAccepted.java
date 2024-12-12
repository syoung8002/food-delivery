package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class OrderAccepted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String userId;
    private String storeId;
    private Long totalAmount;
    private String comment;
    private List<OrderItem> orderItems;
    private Address address;
    private Status status;
}
