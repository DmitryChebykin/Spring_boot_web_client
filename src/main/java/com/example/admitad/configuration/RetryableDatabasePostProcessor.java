package com.example.admitad.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import static com.example.admitad.util.AnsiColor.ANSI_RESET;
import static com.example.admitad.util.AnsiColor.ANSI_YELLOW;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class RetryableDatabasePostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            log.info(ANSI_YELLOW + "-----> configuring a retryable datasource for beanName = {}" + ANSI_RESET, beanName);
            return new RetryableDataSource((DataSource) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
