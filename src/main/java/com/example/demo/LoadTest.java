package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class LoadTest {
    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        ExecutorService es = Executors.newFixedThreadPool(100);

        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8080/rest2?idx={idx}";


        CyclicBarrier barrier = new CyclicBarrier(101);


        for (int i = 0; i < 100; i++) {

            es.submit(() -> {

                int idx = counter.addAndGet(1);
                log.info("Thread " + idx);

                barrier.await();

                StopWatch sw1 = new StopWatch();
                sw1.start();
                String res = rt.getForObject(url, String.class, idx);
                sw1.stop();
                log.info("Elapsed : {} {} / {}", idx , sw1.getTotalTimeSeconds(), res);
                return null;
            });
        }


        barrier.await();

        StopWatch sw = new StopWatch();
        sw.start();


        es.shutdown();
        es.awaitTermination(100, TimeUnit.SECONDS);

        sw.stop();
        log.info("Total time :" + sw.getTotalTimeSeconds());


    }
}
