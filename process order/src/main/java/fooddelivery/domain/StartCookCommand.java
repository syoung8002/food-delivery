package fooddelivery.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class StartCookCommand {

    private Status status;
    private String message;
}
