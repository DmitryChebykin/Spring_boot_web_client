package com.example.admitad.service;

import com.example.admitad.service.scheduler.ActualTokenKeeper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {ActualTokenKeeper.class, TokenReceiver.class})
@ExtendWith(SpringExtension.class)
public class ActualTokenKeeperTest {
    @Autowired
    private ActualTokenKeeper actualTokenKeeper;

    @MockBean
    private TokenReceiver tokenReceiver;

    @Test
    public void testConfigureTasks() {
        ScheduledTaskRegistrar scheduledTaskRegistrar = new ScheduledTaskRegistrar();
        this.actualTokenKeeper.configureTasks(scheduledTaskRegistrar);
        assertTrue(scheduledTaskRegistrar.hasTasks());
    }
}

