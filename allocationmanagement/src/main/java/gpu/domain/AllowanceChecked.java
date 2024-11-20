package gpu.domain;

import gpu.domain.*;
import gpu.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class AllowanceChecked extends AbstractEvent {

    private Long id;
    private String userId;
    private Integer allowedGpu;
    private Integer allowedStorage;
    private Integer allowedCpu;
    private Integer usedCpu;
    private Integer usedGpu;
    private Integer usedStorage;
    private Integer requestedCpu;
    private Integer requestedGpu;
    private Integer requestedStorage;
}
