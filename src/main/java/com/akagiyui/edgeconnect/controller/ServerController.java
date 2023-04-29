package com.akagiyui.edgeconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkagiYui
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }


}
