package fooddelivery.domain;

import fooddelivery.ProcessOrderApplication;
import fooddelivery.domain.OrderRejected;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderMgmt_table")
@Data
//<<< DDD / Aggregate Root
public class OrderMgmt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String userId;

    private String storeId;

    private Integer totalAmount;

    private String comment;

    @ElementCollection
    private List<OrderItem> orderItems;

    @Embedded
    private Status status;

    @Embedded
    private Address address;

    private Integer coupon;

    @PrePersist
    public void onPrePersist() {}

    @PreUpdate
    public void onPreUpdate() {
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    public static OrderMgmtRepository repository() {
        OrderMgmtRepository orderMgmtRepository = ProcessOrderApplication.applicationContext.getBean(
            OrderMgmtRepository.class
        );
        return orderMgmtRepository;
    }

    //<<< Clean Arch / Port Method
    public void processOrder(ProcessOrderCommand processOrderCommand) {
        //implement business logic here:

        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.setStatus(processOrderCommand.getStatus());
        orderAccepted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void startCook(StartCookCommand startCookCommand) {
        //implement business logic here:

        CookStarted cookStarted = new CookStarted(this);
        cookStarted.setStatus(startCookCommand.getStatus());
        cookStarted.setComment(startCookCommand.getMessage());
        cookStarted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void finishCook(FinishCookCommand finishCookCommand) {
        //implement business logic here:

        CookFinished cookFinished = new CookFinished(this);
        cookFinished.setStatus(finishCookCommand.getStatus());
        cookFinished.setComment(finishCookCommand.getMessage());
        cookFinished.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void orderInfoTransfer(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item */
        OrderMgmt orderMgmt = new OrderMgmt();
        orderMgmt.setOrderId(orderPlaced.getId());
        orderMgmt.setUserId(orderPlaced.getUserId());
        orderMgmt.setStoreId(orderPlaced.getStoreId());
        orderMgmt.setTotalAmount(orderPlaced.getTotalAmount());
        orderMgmt.setOrderItems(orderPlaced.getOrderItems());
        orderMgmt.setAddress(orderPlaced.getAddress());
        orderMgmt.setStatus(orderPlaced.getStatus());
        repository().save(orderMgmt);

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(orderMgmt->{
            
            orderMgmt // do something
            repository().save(orderMgmt);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
