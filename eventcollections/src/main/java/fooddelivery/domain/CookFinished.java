package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
public class CookFinished extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String userId;
    private String storeId;
    private Integer totalAmount;
    private String comment;
    private List<OrderItem> orderItems;
    private Address address;
    private Status status;
}
//>>> DDD / Domain Event
