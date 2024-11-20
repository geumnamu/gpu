package gpu.infra;

import gpu.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AllowanceCheckHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<AllowanceCheck>> {

    @Override
    public EntityModel<AllowanceCheck> process(
        EntityModel<AllowanceCheck> model
    ) {
        return model;
    }
}
