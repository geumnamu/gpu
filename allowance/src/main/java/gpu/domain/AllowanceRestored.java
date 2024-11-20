package gpu.domain;

import gpu.domain.*;
import gpu.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AllowanceRestored extends AbstractEvent {

    private Long id;
    private String userId;
    private String allowedCpu;
    private String allowedGpu;
    private String allowedStorage;

    public AllowanceRestored(AllowanceCheck aggregate) {
        super(aggregate);
    }

    public AllowanceRestored() {
        super();
    }
}
//>>> DDD / Domain Event
