package br.com.sicred.impl.kafka.producer;

import br.com.sicred.impl.kafka.mapper.ScheduleAvroMapper;
import br.com.sicred.impl.schedule.model.ScheduleResult;
import br.com.sicred.model.kafka.model.ScheduleResultAvro;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
@EnableKafka
public class ScheduleProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topic.name.schedule-result}")
    private String scheduleResultTopic;

    public void sendAnalysis(ScheduleResult resultMono) {
        ScheduleResultAvro avro = ScheduleAvroMapper.toAvro(resultMono);
        kafkaTemplate.send(scheduleResultTopic, avro);
    }
}
