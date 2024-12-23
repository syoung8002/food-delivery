package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private String storeId;
    private Integer totalAmount;
    private Address address;
    private List<OrderItem> orderItems;
    private Status status;
}
//>>> DDD / Domain Event
