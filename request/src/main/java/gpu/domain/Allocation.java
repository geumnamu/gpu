package gpu.domain;

import gpu.RequestApplication;
import gpu.domain.RequestCancelled;
import gpu.domain.ResourceRequested;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Allocation_table")
@Data
//<<< DDD / Aggregate Root
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Integer numCpu;

    private Integer numGpu;

    private Integer numStorage;

    private String status;

    @PostPersist
    public void onPostPersist() {
        ResourceRequested resourceRequested = new ResourceRequested(this);
        resourceRequested.publishAfterCommit();

        RequestCancelled requestCancelled = new RequestCancelled(this);
        requestCancelled.publishAfterCommit();
    }

    public static AllocationRepository repository() {
        AllocationRepository allocationRepository = RequestApplication.applicationContext.getBean(
            AllocationRepository.class
        );
        return allocationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void changeStatus(Allocated allocated) {
        repository().findById(allocated.getId()).ifPresentOrElse(allocation -> {
            // Update allocation status
            allocation.setStatus(allocated.getStatus());
            allocation.setNumCpu(allocated.getNumCpu());
            allocation.setNumGpu(allocated.getNumGpu());
            allocation.setNumStorage(allocated.getNumStorage());

            repository().save(allocation);
        }, () -> {
            // Create a new allocation if not found
            Allocation newAllocation = new Allocation();
            newAllocation.setId(allocated.getId());
            newAllocation.setUserId(allocated.getUserId());
            newAllocation.setNumCpu(allocated.getNumCpu());
            newAllocation.setNumGpu(allocated.getNumGpu());
            newAllocation.setNumStorage(allocated.getNumStorage());
            newAllocation.setStatus(allocated.getStatus());

            repository().save(newAllocation);
        });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void changeStatus(Retrieved retrieved) {
        repository().findById(retrieved.getId()).ifPresent(allocation -> {
            // Update allocation status
            allocation.setStatus("RETRIEVED");
            allocation.setNumCpu(0);
            allocation.setNumGpu(0);
            allocation.setNumStorage(0);

            repository().save(allocation);
        });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
