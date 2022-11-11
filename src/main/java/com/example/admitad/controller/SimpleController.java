package com.example.admitad.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
public class SimpleController extends AbstractAppController {


    @PostMapping("/ping")
    public ResponseEntity<String> ping() throws IOException {

        Object nativeRequest = nativeWebRequest.getNativeRequest();
        HttpServletRequest httpServletRequest = (HttpServletRequest) nativeRequest;
        Cookie[] cookies = httpServletRequest.getCookies();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        Iterator<String> stringIterator = headerNames.asIterator();
        Stream<String> targetStream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(stringIterator, Spliterator.ORDERED),
                false);

        targetStream.forEach(e -> {
            System.out.print(e + "  :  ");
            System.out.println(httpServletRequest.getHeader(e));
        });
        String reduce = httpServletRequest.getReader().lines().reduce("", String::concat);
        System.out.println("Body : " + reduce);
        return ResponseEntity.ok().body("PONG");
    }
}
