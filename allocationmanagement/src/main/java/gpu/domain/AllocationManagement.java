package gpu.domain;

import gpu.AllocationmanagementApplication;
import gpu.domain.Allocated;
import gpu.domain.Retrieved;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AllocationManagement_table")
@Data
//<<< DDD / Aggregate Root
public class AllocationManagement {

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
        Allocated allocated = new Allocated(this);
        allocated.publishAfterCommit();

        Retrieved retrieved = new Retrieved(this);
        retrieved.publishAfterCommit();
    }

    public static AllocationManagementRepository repository() {
        AllocationManagementRepository allocationManagementRepository = AllocationmanagementApplication.applicationContext.getBean(
            AllocationManagementRepository.class
        );
        return allocationManagementRepository;
    }

    //<<< Clean Arch / Port Method
    public static void requestReceived(AllowanceChecked allowanceChecked) {
        //implement business logic here:

        /** Example 1:  new item 
        AllocationManagement allocationManagement = new AllocationManagement();
        repository().save(allocationManagement);

        */

        /** Example 2:  finding and process
        
        repository().findById(allowanceChecked.get???()).ifPresent(allocationManagement->{
            
            allocationManagement // do something
            repository().save(allocationManagement);


         });
        */

        repository().findById(allowanceChecked.getId()).ifPresentOrElse(allocationManagement -> {
            // Update the allocation if it exists
            allocationManagement.setNumCpu(
                allowanceChecked.getRequestedCpu()
            );
            allocationManagement.setNumGpu(
                allowanceChecked.getRequestedGpu()
            );
            allocationManagement.setNumStorage(
                allowanceChecked.getRequestedStorage()
            );
            allocationManagement.setStatus("UPDATED");
            repository().save(allocationManagement);
        }, () -> {
            // Create a new allocation if it doesn't exist
            AllocationManagement allocationManagement = new AllocationManagement();
            allocationManagement.setUserId(allowanceChecked.getUserId());
            allocationManagement.setNumCpu(
                allowanceChecked.getRequestedCpu()
            );
            allocationManagement.setNumGpu(
                allowanceChecked.getRequestedGpu()
            );
            allocationManagement.setNumStorage(
                allowanceChecked.getRequestedStorage()
            );
            allocationManagement.setStatus("CREATED");
            repository().save(allocationManagement);
        });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void allocationCancel(AllowanceRestored allowanceRestored) {
        //implement business logic here:

        /** Example 1:  new item 
        AllocationManagement allocationManagement = new AllocationManagement();
        repository().save(allocationManagement);

        */

        /** Example 2:  finding and process
        
        repository().findById(allowanceRestored.get???()).ifPresent(allocationManagement->{
            
            allocationManagement // do something
            repository().save(allocationManagement);


         });
        */
        repository().findById(allowanceRestored.getId()).ifPresent(allocationManagement -> {
            // Reset the allocation to reflect cancellation
            allocationManagement.setNumCpu(0);
            allocationManagement.setNumGpu(0);
            allocationManagement.setNumStorage(0);
            allocationManagement.setStatus("CANCELLED");
            repository().save(allocationManagement);
        });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
