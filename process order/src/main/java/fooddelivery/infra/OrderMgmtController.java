package fooddelivery.infra;

import fooddelivery.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/orderMgmts")
@Transactional
public class OrderMgmtController {

    @Autowired
    OrderMgmtRepository orderMgmtRepository;

    @RequestMapping(
        value = "/orderMgmts/{id}/processorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public OrderMgmt processOrder(
        @PathVariable(value = "id") Long id,
        @RequestBody ProcessOrderCommand processOrderCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /orderMgmt/processOrder  called #####");
        Optional<OrderMgmt> optionalOrderMgmt = orderMgmtRepository.findById(
            id
        );

        optionalOrderMgmt.orElseThrow(() -> new Exception("No Entity Found"));
        OrderMgmt orderMgmt = optionalOrderMgmt.get();
        orderMgmt.processOrder(processOrderCommand);

        orderMgmtRepository.save(orderMgmt);
        return orderMgmt;
    }

    @RequestMapping(
        value = "/orderMgmts/{id}/startcook",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public OrderMgmt startCook(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /orderMgmt/startCook  called #####");
        Optional<OrderMgmt> optionalOrderMgmt = orderMgmtRepository.findById(
            id
        );

        optionalOrderMgmt.orElseThrow(() -> new Exception("No Entity Found"));
        OrderMgmt orderMgmt = optionalOrderMgmt.get();
        orderMgmt.startCook();

        orderMgmtRepository.save(orderMgmt);
        return orderMgmt;
    }

    @RequestMapping(
        value = "/orderMgmts/{id}/finishcook",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public OrderMgmt finishCook(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /orderMgmt/finishCook  called #####");
        Optional<OrderMgmt> optionalOrderMgmt = orderMgmtRepository.findById(
            id
        );

        optionalOrderMgmt.orElseThrow(() -> new Exception("No Entity Found"));
        OrderMgmt orderMgmt = optionalOrderMgmt.get();
        orderMgmt.finishCook();

        orderMgmtRepository.save(orderMgmt);
        return orderMgmt;
    }
}
//>>> Clean Arch / Inbound Adaptor
