package gpu.infra;

import gpu.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AllocationManagementHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<AllocationManagement>> {

    @Override
    public EntityModel<AllocationManagement> process(
        EntityModel<AllocationManagement> model
    ) {
        return model;
    }
}
