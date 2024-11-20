package gpu.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gpu.config.kafka.KafkaProcessor;
import gpu.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    AllocationRepository allocationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Allocated'"
    )
    public void wheneverAllocated_ChangeStatus(@Payload Allocated allocated) {
        Allocated event = allocated;
        System.out.println(
            "\n\n##### listener ChangeStatus : " + allocated + "\n\n"
        );

        // Sample Logic //
        Allocation.changeStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Retrieved'"
    )
    public void wheneverRetrieved_ChangeStatus(@Payload Retrieved retrieved) {
        Retrieved event = retrieved;
        System.out.println(
            "\n\n##### listener ChangeStatus : " + retrieved + "\n\n"
        );

        // Sample Logic //
        Allocation.changeStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
