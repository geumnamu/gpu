package gpu.domain;

import gpu.AllowanceApplication;
import gpu.domain.AllowanceChecked;
import gpu.domain.AllowanceRestored;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AllowanceCheck_table")
@Data
//<<< DDD / Aggregate Root
public class AllowanceCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Integer allowedCpu;

    private Integer allowedGpu;

    private Integer allowedStorage;

    private Integer usedCpu;

    private Integer usedGpu;

    private Integer usedStorage;

    private Integer requestedCpu;

    private Integer requestedGpu;

    private Integer requestedStorage;

    @PostPersist
    public void onPostPersist() {
        AllowanceChecked allowanceChecked = new AllowanceChecked(this);
        allowanceChecked.publishAfterCommit();

        AllowanceRestored allowanceRestored = new AllowanceRestored(this);
        allowanceRestored.publishAfterCommit();
    }

    public static AllowanceCheckRepository repository() {
        AllowanceCheckRepository allowanceCheckRepository = AllowanceApplication.applicationContext.getBean(
            AllowanceCheckRepository.class
        );
        return allowanceCheckRepository;
    }

    //<<< Clean Arch / Port Method
    public static void checkAllowance(ResourceRequested resourceRequested) {

        repository().findById(resourceRequested.getId()).ifPresentOrElse(allowanceCheck -> {
            // Update the allowance based on the resource request
            boolean withinCpuLimit = resourceRequested.getNumCpu() <= (allowanceCheck.getAllowedCpu() - allowanceCheck.getUsedCpu());
            boolean withinGpuLimit = resourceRequested.getNumGpu() <= (allowanceCheck.getAllowedGpu() - allowanceCheck.getUsedGpu());
            boolean withinStorageLimit = resourceRequested.getNumStorage() <= (allowanceCheck.getAllowedStorage() - allowanceCheck.getUsedStorage());

            if (withinCpuLimit && withinGpuLimit && withinStorageLimit) {
                // Update the allowance with the request if valid
                allowanceCheck.setRequestedCpu(resourceRequested.getNumCpu());
                allowanceCheck.setRequestedGpu(resourceRequested.getNumGpu());
                allowanceCheck.setRequestedStorage(resourceRequested.getNumStorage());

                allowanceCheck.setUsedCpu(allowanceCheck.getUsedCpu() + resourceRequested.getNumCpu());
                allowanceCheck.setUsedGpu(allowanceCheck.getUsedGpu() + resourceRequested.getNumGpu());
                allowanceCheck.setUsedStorage(allowanceCheck.getUsedStorage() + resourceRequested.getNumStorage());
                allowanceCheck.setUserId(resourceRequested.getUserId());
            } else {
                System.out.println("Requested resources exceed allowed limits.");
                allowanceCheck.setRequestedCpu(0);
                allowanceCheck.setRequestedGpu(0);
                allowanceCheck.setRequestedStorage(0);
            }
  
            AllowanceChecked allowanceChecked = new AllowanceChecked(allowanceCheck);
            allowanceChecked.publishAfterCommit();
            
            repository().save(allowanceCheck);
        }, () -> {
            // Create new allowance check entry if not found
            AllowanceCheck allowanceCheck = new AllowanceCheck();
            allowanceCheck.setAllowedCpu(3); // Example default allowance
            allowanceCheck.setAllowedGpu(0);
            allowanceCheck.setAllowedStorage(10);
            allowanceCheck.setUsedCpu(0);
            allowanceCheck.setUsedGpu(0);
            allowanceCheck.setUsedStorage(0);
            allowanceCheck.setUserId(resourceRequested.getUserId());


            boolean withinCpuLimit = resourceRequested.getNumCpu() <= (allowanceCheck.getAllowedCpu() - allowanceCheck.getUsedCpu());
            boolean withinGpuLimit = resourceRequested.getNumGpu() <= (allowanceCheck.getAllowedGpu() - allowanceCheck.getUsedGpu());
            boolean withinStorageLimit = resourceRequested.getNumStorage() <= (allowanceCheck.getAllowedStorage() - allowanceCheck.getUsedStorage());
            if (withinCpuLimit && withinGpuLimit && withinStorageLimit) {
                System.out.println("Here");
                // Update the allowance with the request if valid
                allowanceCheck.setRequestedCpu(resourceRequested.getNumCpu());
                allowanceCheck.setRequestedGpu(resourceRequested.getNumGpu());
                allowanceCheck.setRequestedStorage(resourceRequested.getNumStorage());
                
                allowanceCheck.setUsedCpu(allowanceCheck.getUsedCpu() + resourceRequested.getNumCpu());
                allowanceCheck.setUsedGpu(allowanceCheck.getUsedGpu() + resourceRequested.getNumGpu());
                allowanceCheck.setUsedStorage(allowanceCheck.getUsedStorage() + resourceRequested.getNumStorage());


            } else {
                System.out.println("Requested resources exceed allowed limits.");
                allowanceCheck.setRequestedCpu(0);
                allowanceCheck.setRequestedGpu(0);
                allowanceCheck.setRequestedStorage(0);
            }
            AllowanceChecked allowanceChecked = new AllowanceChecked(allowanceCheck);
            allowanceChecked.publishAfterCommit();
            repository().save(allowanceCheck);
        });
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void requestCancelled(RequestCancelled requestCancelled) {
        //implement business logic here:

        /** Example 1:  new item 
        AllowanceCheck allowanceCheck = new AllowanceCheck();
        repository().save(allowanceCheck);

        AllowanceRestored allowanceRestored = new AllowanceRestored(allowanceCheck);
        allowanceRestored.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(requestCancelled.get???()).ifPresent(allowanceCheck->{
            
            allowanceCheck // do something
            repository().save(allowanceCheck);

            AllowanceRestored allowanceRestored = new AllowanceRestored(allowanceCheck);
            allowanceRestored.publishAfterCommit();

         });
        */

       repository().findById(requestCancelled.getId()).ifPresent(allowanceCheck -> {
            // Reset requested resources
            allowanceCheck.setUsedCpu(allowanceCheck.getUsedCpu() - requestCancelled.getNumCpu());
            allowanceCheck.setUsedGpu(allowanceCheck.getUsedGpu() - requestCancelled.getNumGpu());
            allowanceCheck.setUsedStorage(allowanceCheck.getUsedStorage() - requestCancelled.getNumStorage());
            repository().save(allowanceCheck);
        });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
