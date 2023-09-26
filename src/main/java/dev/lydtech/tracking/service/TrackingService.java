package dev.lydtech.tracking.service;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.dispatch.message.TrackingStatusUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackingService {

    private static final String TRACKING_STATUS_TOPIC = "tracking.status";
    private final KafkaTemplate<String, Object> kafkaProducer;
    public void process(DispatchPreparing dispatchPreparing) {
        log.info("Received dispatch preparing message : " + dispatchPreparing);
        TrackingStatusUpdate trackingStatusUpdate = new TrackingStatusUpdate(dispatchPreparing.getOrderId(), "PREPARING");
        //TODO need a producer to send
        kafkaProducer.send(TRACKING_STATUS_TOPIC, trackingStatusUpdate);
    }

}
