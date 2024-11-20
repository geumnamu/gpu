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
    private Integer allowedCpu;
    private Integer allowsGpu;
    private Integer allowsStorage;
}
