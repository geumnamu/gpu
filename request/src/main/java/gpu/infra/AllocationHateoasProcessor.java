package gpu.infra;

import gpu.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AllocationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Allocation>> {

    @Override
    public EntityModel<Allocation> process(EntityModel<Allocation> model) {
        return model;
    }
}
