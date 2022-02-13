package com.example.admitad.service.scheduler;

import com.example.admitad.service.TokenReceiver;
import com.example.admitad.tokenModel.TokenData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;

@RequiredArgsConstructor
@Service
@Slf4j
public class ActualTokenKeeper implements SchedulingConfigurer {
    public static final int MINIMUM_SECOND_TIMEOUT = 5;

    public static final int SECOND_BEFORE_EXPIRES = 10;

    private final TokenReceiver tokenReceiver;

    @Getter
    private TokenData tokenData;

    @PostConstruct
    private void init() {
        tokenData = tokenReceiver.getTokenData();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(this::setTokenData, this::getNextDate);
    }

    private Date getNextDate(TriggerContext triggerContext) {
        Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
        Calendar nextExecutionTime = Calendar.getInstance();

        if (lastActualExecutionTime == null) {
            nextExecutionTime.setTime(new Date());
        } else {
            nextExecutionTime.setTime(lastActualExecutionTime);
        }

        Integer expiresIn = tokenData.getExpiresIn();

        if (expiresIn == null) {
            expiresIn = 0;
        }

        int seconds = expiresIn - SECOND_BEFORE_EXPIRES;

        nextExecutionTime.add(Calendar.SECOND, Math.max(MINIMUM_SECOND_TIMEOUT, seconds));

        return nextExecutionTime.getTime();
    }

    private void setTokenData() {
        if (tokenData == null || tokenData.getRefreshToken() == null) {
            tokenData = tokenReceiver.getTokenData();
        } else {
            String refreshToken = tokenData.getRefreshToken();
            tokenData = tokenReceiver.refreshTokenData(refreshToken);
        }
        log.info(String.valueOf(tokenData));
    }
}