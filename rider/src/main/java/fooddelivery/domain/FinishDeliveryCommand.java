package fooddelivery.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class FinishDeliveryCommand {

    private String status;
}
