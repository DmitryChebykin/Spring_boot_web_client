package com.example.admitad.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger = LoggerFactory.getLogger(StartUpListener.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {



        // лог файлов в докере с расширением .properties
        Iterable<Path> rootDirectories = FileSystems.getDefault().getRootDirectories();
        Stream<Path> stream = StreamSupport.stream(rootDirectories.spliterator(), false);

        stream.filter(p -> !Files.isDirectory(p))
                .map(p -> p.toAbsolutePath().toString().toLowerCase())
                .filter(f -> f.endsWith(".properties"))
                .forEach(l -> logger.debug("full path : {}", l));

    }
}
