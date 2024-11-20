package gpu.domain;

import gpu.domain.*;
import gpu.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ResourceRequested extends AbstractEvent {

    private Long id;
    private String userId;
    private String status;
    private Integer numCpu;
    private Integer numGpu;
    private Integer numStorage;

    public ResourceRequested(Allocation aggregate) {
        super(aggregate);
    }

    public ResourceRequested() {
        super();
    }
}
//>>> DDD / Domain Event
