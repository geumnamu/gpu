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
    AllowanceCheckRepository allowanceCheckRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ResourceRequested'"
    )
    public void wheneverResourceRequested_CheckAllowance(
        @Payload ResourceRequested resourceRequested
    ) {
        ResourceRequested event = resourceRequested;
        System.out.println(
            "\n\n##### listener CheckAllowance : " + resourceRequested + "\n\n"
        );

        // Sample Logic //
        AllowanceCheck.checkAllowance(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='RequestCancelled'"
    )
    public void wheneverRequestCancelled_RequestCancelled(
        @Payload RequestCancelled requestCancelled
    ) {
        RequestCancelled event = requestCancelled;
        System.out.println(
            "\n\n##### listener RequestCancelled : " + requestCancelled + "\n\n"
        );

        // Sample Logic //
        AllowanceCheck.requestCancelled(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
