package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private String storeId;
    private Integer totalAmount;
    private Address address;
    private List<OrderItem> orderItems;
    private Status status;
}
