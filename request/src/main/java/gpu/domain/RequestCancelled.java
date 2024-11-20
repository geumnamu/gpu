package gpu.domain;

import gpu.domain.*;
import gpu.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class RequestCancelled extends AbstractEvent {

    private Long id;
    private String userId;
    private String numCpu;
    private String numGpu;
    private String numStorage;
    private String status;

    public RequestCancelled(Allocation aggregate) {
        super(aggregate);
    }

    public RequestCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
