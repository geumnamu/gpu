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

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ResourceRequested'"
    )
    public void wheneverResourceRequested_KakaoTalkNotification(
        @Payload ResourceRequested resourceRequested
    ) {
        ResourceRequested event = resourceRequested;
        System.out.println(
            "\n\n##### listener KakaoTalkNotification : " +
            resourceRequested +
            "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='RequestCancelled'"
    )
    public void wheneverRequestCancelled_KakaoTalkNotification(
        @Payload RequestCancelled requestCancelled
    ) {
        RequestCancelled event = requestCancelled;
        System.out.println(
            "\n\n##### listener KakaoTalkNotification : " +
            requestCancelled +
            "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Allocated'"
    )
    public void wheneverAllocated_KakaoTalkNotification(
        @Payload Allocated allocated
    ) {
        Allocated event = allocated;
        System.out.println(
            "\n\n##### listener KakaoTalkNotification : " + allocated + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Retrieved'"
    )
    public void wheneverRetrieved_KakaoTalkNotification(
        @Payload Retrieved retrieved
    ) {
        Retrieved event = retrieved;
        System.out.println(
            "\n\n##### listener KakaoTalkNotification : " + retrieved + "\n\n"
        );
        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
