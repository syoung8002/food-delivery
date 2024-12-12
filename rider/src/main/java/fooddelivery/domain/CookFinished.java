package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookFinished extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String userId;
    private String storeId;
    private Integer totalAmount;
    private String comment;
    private Object orderItems;
    private Object address;
    private Object status;
}
