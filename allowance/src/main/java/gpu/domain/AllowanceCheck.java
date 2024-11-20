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
        //implement business logic here:

        /** Example 1:  new item 
        AllowanceCheck allowanceCheck = new AllowanceCheck();
        repository().save(allowanceCheck);

        */

        /** Example 2:  finding and process
        
        repository().findById(resourceRequested.get???()).ifPresent(allowanceCheck->{
            
            allowanceCheck // do something
            repository().save(allowanceCheck);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void requestCancelled(RequestCancelled requestCancelled) {
        //implement business logic here:

        /** Example 1:  new item 
        AllowanceCheck allowanceCheck = new AllowanceCheck();
        repository().save(allowanceCheck);

        */

        /** Example 2:  finding and process
        
        repository().findById(requestCancelled.get???()).ifPresent(allowanceCheck->{
            
            allowanceCheck // do something
            repository().save(allowanceCheck);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
