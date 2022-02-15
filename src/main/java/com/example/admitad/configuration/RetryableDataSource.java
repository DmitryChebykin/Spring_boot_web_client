package com.example.admitad.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.example.admitad.util.AnsiColor.ANSI_RESET;
import static com.example.admitad.util.AnsiColor.ANSI_YELLOW;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RetryableDataSource extends AbstractDataSource {

    private final DataSource dataSource;

    @Override
    @Retryable(maxAttempts = 5, backoff = @Backoff(multiplier = 1.3, maxDelay = 10000))
    public Connection getConnection() throws SQLException {
        log.info(ANSI_YELLOW + "getting connection ..." + ANSI_RESET);
        log.info(ANSI_YELLOW + "Retry Number : {}" + ANSI_RESET, RetrySynchronizationManager.getContext().getRetryCount());
        return dataSource.getConnection();
    }

    @Override
    @Retryable(maxAttempts = 5, backoff = @Backoff(multiplier = 2.3, maxDelay = 10000))
    public Connection getConnection(String username, String password) throws SQLException {
        log.info("getting connection by username and password ...");
        return dataSource.getConnection(username, password);
    }
}

