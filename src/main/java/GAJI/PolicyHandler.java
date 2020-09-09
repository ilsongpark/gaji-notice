package GAJI;

import GAJI.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    NoticeRepository noticeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverStatusChanged_Send(@Payload StatusChanged statusChanged){

        if(statusChanged.isMe()){
            System.out.println("##### listener Send : " + statusChanged.toJson());

            Notice notice = new Notice();
            notice.setMemberId(statusChanged.getMemberId());
            notice.setMsg(statusChanged.getProductName().toString() +  ":" + statusChanged.getStatus().toString());

            noticeRepository.save(notice);
        }
    }

}
