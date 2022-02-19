package com.example.admitad.service.scheduler;

import com.example.admitad.service.DataService;
import com.example.admitad.service.ProgramJsonReceiver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ActualJsonKeeper {
    private final ProgramJsonReceiver programJsonReceiver;

    private final DataService dataService;

    private final CircularFifoQueue<Optional<JSONObject>> circularFifoQueue = new CircularFifoQueue<>(1);

    @Scheduled(cron = "*/30 * * * * *")
    public void configureTasks() {
        Optional<JSONObject> jsonObject = programJsonReceiver.getJsonObject();

        if (jsonObject.isPresent()) {
            JSONObject object = jsonObject.get();
            log.info(String.valueOf(object));
            circularFifoQueue.add(jsonObject);
        }
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void saveJsonToDB() {
        if (circularFifoQueue.isEmpty()) {
            log.info("Нет свежих данных");
        } else {
            Optional<JSONObject> poll = circularFifoQueue.poll();

            poll.ifPresent(jsonObject -> dataService.saveJsonToDB(jsonObject));
        }
    }
}