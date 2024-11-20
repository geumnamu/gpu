package gpu.domain;

import gpu.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class Allocated extends AbstractEvent {

    private Long id;
    private String userId;
    private String status;
    private Integer numCpu;
    private Integer numGpu;
    private Integer numStorage;
}
