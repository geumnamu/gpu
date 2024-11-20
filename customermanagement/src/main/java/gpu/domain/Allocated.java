package gpu.domain;

import gpu.domain.*;
import gpu.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Allocated extends AbstractEvent {

    private Long id;
    private String userId;
    private String status;
    private Integer numCpu;
    private Integer numGpu;
    private Integer numStorage;
}
