package com.example.admitad.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DBInitCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ApplicationArguments applicationArguments = Objects.requireNonNull(context.getBeanFactory()).getBean(ApplicationArguments.class);

        String[] sourceArgs = applicationArguments.getSourceArgs();
        List<String> stringList = Arrays.stream(sourceArgs).collect(Collectors.toList());
        return stringList.contains("--init");
    }
}