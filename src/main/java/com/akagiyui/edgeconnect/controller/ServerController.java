package com.akagiyui.edgeconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器操作 Controller
 * @author AkagiYui
 */
@RestController
@RequestMapping("/server")
public class ServerController {
    /**
     * 心跳检测
     * @return "pong"
     */
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
