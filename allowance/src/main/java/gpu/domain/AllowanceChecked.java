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
    private String allowedCpu;
    private String allowedGpu;
    private String allowedStorage;

    public AllowanceChecked(AllowanceCheck aggregate) {
        super(aggregate);
    }

    public AllowanceChecked() {
        super();
    }
}
//>>> DDD / Domain Event
