package com.springboot.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Service //将服务发布出去   是dubbo的@Service注解
@Component
public class TicketServiceImpl implements TicketService {

    @Value("${dubbo.protocol.port}")
    String port;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    }) //不写默认阀值为5秒20次
    @Override
    public String getTicket() {
        throw new RuntimeException("Exception to show hsytrix enabled.");
        //return "《厉害了，我的国》 from port:"+port;
    }
}
