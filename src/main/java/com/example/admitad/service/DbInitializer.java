package com.example.admitad.service;

import com.example.admitad.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

import static com.example.admitad.util.AnsiColor.ANSI_RESET;
import static com.example.admitad.util.AnsiColor.ANSI_YELLOW;

@RequiredArgsConstructor
@Component
@Slf4j
@Order(1)
@Conditional(DBInitCondition.class)
public class DbInitializer {
    private final TariffRepository tariffRepository;
    private final RatesRepository ratesRepository;
    private final CategoryRepository categoryRepository;
    private final ProgramRepository programRepository;
    private final ActionsDetailRepository actionsDetailRepository;


    @PostConstruct
    public void run() {
        getRepositoryFields().forEach(this::initDB);
    }

    private <T extends BaseRepository> void initDB(T t) {
        t.dropTableIfExists();
        log.info(ANSI_YELLOW + "drop " + t.getName() + " table" + ANSI_RESET);
        t.createTableIfMissing();
        log.info(ANSI_YELLOW + "create " + t.getName() + " table" + ANSI_RESET);
    }

    private List<? extends BaseRepository> getRepositoryFields() {
        return Arrays.asList(tariffRepository, ratesRepository, categoryRepository, programRepository, actionsDetailRepository);
    }
}