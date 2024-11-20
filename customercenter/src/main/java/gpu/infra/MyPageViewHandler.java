package gpu.infra;

import gpu.config.kafka.KafkaProcessor;
import gpu.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MyPageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAllocated_then_CREATE_1(@Payload Allocated allocated) {
        try {
            if (!allocated.validate()) return;

            // view 객체 생성
            MyPage myPage = new MyPage();
            // view 객체에 이벤트의 Value 를 set 함
            myPage.setId(allocated.getId());
            myPage.setUserId(allocated.getUserId());
            myPage.setNumCpu(allocated.getNumCpu());
            myPage.setNumGpu(allocated.getNumGpu());
            myPage.setNumStorage(allocated.getNumStorage());
            myPage.setStatus(Integer.parseInt(allocated.getStatus()));
            // view 레파지 토리에 save
            myPageRepository.save(myPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
