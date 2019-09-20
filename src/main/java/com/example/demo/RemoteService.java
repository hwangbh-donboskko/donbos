package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RemoteService {

    @RestController
    public static class MyController{
        @RequestMapping("/service")
        public String rest(String req) throws InterruptedException {
            Thread.sleep(2000);
            //throw new RuntimeException();
            return req + "/service1";
        }

        @RequestMapping("/service2")
        public String rest2(String req) throws InterruptedException {
            Thread.sleep(2000);
            return req + "/service2";

        }
    }



    public static void main(String[] args) {
         System.setProperty("SERVER.PORT", "8088");
         System.setProperty("server.tomcat.max-threads", "1000");
        SpringApplication.run(RemoteService.class, args);

    }
}
