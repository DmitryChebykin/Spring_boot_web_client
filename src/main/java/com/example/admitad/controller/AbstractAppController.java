package com.example.admitad.controller;

import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Resource;

public abstract class AbstractAppController {
    @Resource
    protected   NativeWebRequest nativeWebRequest;
}
