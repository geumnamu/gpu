package gpu.infra;

import gpu.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "computerResources",
    path = "computerResources"
)
public interface ComputerResourcesRepository
    extends PagingAndSortingRepository<ComputerResources, Long> {}
