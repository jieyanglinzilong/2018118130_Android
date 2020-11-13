package com.atguigu.springcloud.controller;



import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @auther zzyy
 * @create 2020-02-19 16:06
 */
@RestController
@Slf4j
public class PaymentController
{
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul()
    {
        return "springcloud with consul: "+serverPort+"\t   "+ UUID.randomUUID().toString();
    }
}
