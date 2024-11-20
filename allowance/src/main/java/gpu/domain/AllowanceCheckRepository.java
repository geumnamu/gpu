package gpu.domain;

import gpu.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "allowanceChecks",
    path = "allowanceChecks"
)
public interface AllowanceCheckRepository
    extends PagingAndSortingRepository<AllowanceCheck, Long> {}
