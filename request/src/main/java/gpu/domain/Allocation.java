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
        //implement business logic here:

        /** Example 1:  new item 
        Allocation allocation = new Allocation();
        repository().save(allocation);

        */

        /** Example 2:  finding and process
        
        repository().findById(allocated.get???()).ifPresent(allocation->{
            
            allocation // do something
            repository().save(allocation);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void changeStatus(Retrieved retrieved) {
        //implement business logic here:

        /** Example 1:  new item 
        Allocation allocation = new Allocation();
        repository().save(allocation);

        */

        /** Example 2:  finding and process
        
        repository().findById(retrieved.get???()).ifPresent(allocation->{
            
            allocation // do something
            repository().save(allocation);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
