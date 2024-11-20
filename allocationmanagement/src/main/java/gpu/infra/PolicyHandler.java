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
    AllocationManagementRepository allocationManagementRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AllowanceChecked'"
    )
    public void wheneverAllowanceChecked_RequestReceived(
        @Payload AllowanceChecked allowanceChecked
    ) {
        AllowanceChecked event = allowanceChecked;
        System.out.println(
            "\n\n##### listener RequestReceived : " + allowanceChecked + "\n\n"
        );

        // Sample Logic //
        AllocationManagement.requestReceived(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AllowanceRestored'"
    )
    public void wheneverAllowanceRestored_AllocationCancel(
        @Payload AllowanceRestored allowanceRestored
    ) {
        AllowanceRestored event = allowanceRestored;
        System.out.println(
            "\n\n##### listener AllocationCancel : " +
            allowanceRestored +
            "\n\n"
        );

        // Sample Logic //
        AllocationManagement.allocationCancel(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
