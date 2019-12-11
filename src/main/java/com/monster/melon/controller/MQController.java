package com.monster.melon.controller;

import com.monster.melon.mq.exchange.dead.DeadSender;
import com.monster.melon.mq.hello.HelloSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class MQController {

    private final HelloSender sender;
    private final DeadSender deadSender;

    public MQController(HelloSender sender, DeadSender deadSender) {
        this.sender = sender;
        this.deadSender = deadSender;
    }


    @GetMapping("/{msg}")
    public String hello(@PathVariable("msg") String msg){
        for (int i = 0; i < 1; i++) {
            sender.send(msg);
        }
        return "success";
    }
}
