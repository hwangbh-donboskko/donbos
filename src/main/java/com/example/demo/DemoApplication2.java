package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;


@SpringBootApplication
@Slf4j
@EnableAsync
public class DemoApplication2 {




	@RestController
	public static  class MyController{
		Queue<DeferredResult<String>> results = new ConcurrentLinkedQueue<>();

		@Autowired
		DemoApplication1.MyService myService;

		@GetMapping("/async")
		public String async() throws InterruptedException {

			Thread.sleep(5000);
			return "hello";
		}
		@GetMapping("/callable")
		public Callable<String> callable() throws InterruptedException {
			log.info("callable start");
			return ()-> {
				log.info("callable return start");
				Thread.sleep(5000);
				return "hello11111";
			};
		}

		@GetMapping("/dr")
		public DeferredResult<String> dr(){

			log.info("dr");
			DeferredResult<String> dr = new DeferredResult<>(600000L);
			results.add(dr);
			return dr;


		}
		@GetMapping("/dr/count")
		public String drcount(){

			return String.valueOf(results.size());
		}

		@GetMapping("/dr/event")
		public String drevent(String msg){
			for(DeferredResult<String> dr : results){
				dr.setResult("Hello " + msg);
				results.remove(dr);
			}
			return "OK";

		}

		@GetMapping("/emitter")
		public ResponseBodyEmitter emitter(){

			ResponseBodyEmitter emitter = new ResponseBodyEmitter();
			Executors.newSingleThreadExecutor().submit(()->{
				try {
					for(int i =1; i <=50 ; i++){
						emitter.send("<p> Stream " + i + "</p>" );
					Thread.sleep(200);
				}
				} catch (Exception e) {
				}
			});

			return emitter;
		}




	}

	public static void main(String[] args) {
	SpringApplication.run(DemoApplication2.class, args);

	}

}
