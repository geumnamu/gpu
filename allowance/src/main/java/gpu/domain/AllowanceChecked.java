package gpu.domain;

import gpu.domain.*;
import gpu.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AllowanceChecked extends AbstractEvent {

    private Long id;
    private String userId;
    private Integer allowedCpu;
    private Integer allowsGpu;
    private Integer allowsStorage;

    public AllowanceChecked(AllowanceCheck aggregate) {
        super(aggregate);
    }

    public AllowanceChecked() {
        super();
    }
}
//>>> DDD / Domain Event
