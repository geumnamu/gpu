package gpu.domain;

import gpu.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "allocationManagements",
    path = "allocationManagements"
)
public interface AllocationManagementRepository
    extends PagingAndSortingRepository<AllocationManagement, Long> {}
