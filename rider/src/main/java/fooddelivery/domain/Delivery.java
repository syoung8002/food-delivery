package fooddelivery.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import fooddelivery.RiderApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String userId;

    private String receiverAddr;

    private String riderId;

    private String message;

    @PostPersist
    public void onPostPersist() {}

    @PreUpdate
    public void onPreUpdate() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public void pick(PickCommand pickCommand) {
        //implement business logic here:

        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.setRiderId(pickCommand.getRiderId());
        deliveryStarted.setMessage(pickCommand.getMessage());
        deliveryStarted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void finishDelivery(FinishDeliveryCommand finishDeliveryCommand) {
        //implement business logic here:

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.setMessage(finishDeliveryCommand.getMessage());
        deliveryCompleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void deliveryInfoTransfer(CookFinished cookFinished) {
        //implement business logic here:

        /** Example 1:  new item  */
        Delivery delivery = new Delivery();
        delivery.setOrderId(cookFinished.getOrderId());
        delivery.setUserId(cookFinished.getUserId());
        delivery.setReceiverAddr(cookFinished.getAddress().toString());
        repository().save(delivery);

        /** Example 2:  finding and process
        

        repository().findById(cookFinished.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
