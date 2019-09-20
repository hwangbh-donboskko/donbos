package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@Slf4j
@EnableAsync
public class DemoApplication1 {

	@Component
  	 public static class MyService{

		@Async(value="tp1")
		public ListenableFuture<String> hello() throws InterruptedException {
			log.warn("hello()");
			Thread.sleep(1000);
			log.warn("hello()aaaaaaaaaa");

			return new AsyncResult<>( "hello");
		}
	}

	public static void main(String[] args) {
		try(ConfigurableApplicationContext c = SpringApplication.run(DemoApplication1.class, args))
		{

		}


	}
	@Autowired
	MyService myService;

	@Bean
	ThreadPoolTaskExecutor tp(){
		ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
		tp.setCorePoolSize(10);
		tp.setMaxPoolSize(100);
		tp.setQueueCapacity(200);
		tp.setThreadNamePrefix("task111-");
		tp.initialize();
		return tp;
	}

	@Bean
	ThreadPoolTaskExecutor tp1(){
		ThreadPoolTaskExecutor tp1 = new ThreadPoolTaskExecutor();
		tp1.setCorePoolSize(10);
		tp1.setMaxPoolSize(100);
		tp1.setQueueCapacity(200);
		tp1.setThreadNamePrefix("task222-");
		tp1.initialize();
		return tp1;
	}
	
	@Bean
 	ApplicationRunner run(){

		return args ->{

			log.warn("run()");
			ListenableFuture<String> res  = myService.hello();
			 res.addCallback(System.out::println, e-> log.info(""+ e.getMessage()) );
			log.info("exit    " );

		};
	}


	@RestController
	public static class tController{

		@RequestMapping("/hello")
		public Publisher<String> hello(String name){

			return new Publisher<String>() {
				@Override
				public void subscribe(Subscriber<? super String> s) {
					s.onSubscribe(new Subscription() {
						@Override
						public void request(long n) {
							s.onNext("Hello " + name);
							s.onComplete();
						}

						@Override
						public void cancel() {

						}
					});

				}
			};
		}

	}
}
