package com.example.demo.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GrpcRunner implements ApplicationRunner {

    private Server server;
    private int port;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=======================");
        port = 9595;
        server = ServerBuilder.forPort(port)
                .addService(new NewdataServiceImpl())
                .build(); this.server.start();

        log.warn("gRPC Server Listening on port " + port);
        this.server.awaitTermination();
    }
}
