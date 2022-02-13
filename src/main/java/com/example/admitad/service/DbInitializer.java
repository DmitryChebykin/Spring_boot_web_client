package com.example.admitad.service;

import com.example.admitad.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.example.admitad.util.AnsiColor.ANSI_RESET;
import static com.example.admitad.util.AnsiColor.ANSI_YELLOW;

@RequiredArgsConstructor
@Component
@Slf4j
@Order(1)
@Conditional(DBInitCondition.class)
public class DbInitializer {
    private final TariffRepository tariffRepository;

    @PostConstruct
    public void run() {
        InitDB();
    }

    private void InitDB() {
        tariffRepository.dropTableIfExists();
        log.info(ANSI_YELLOW + "drop Tariff table" + ANSI_RESET);
        tariffRepository.createTableIfMissing();
        log.info(ANSI_YELLOW + "create Tariff table" + ANSI_RESET);
    }
}