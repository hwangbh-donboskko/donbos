package com.example.demo;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;


@SpringBootApplication
@Slf4j
@EnableAsync
@SuppressWarnings("deprecated")
public class DemoApplication {



    @RestController
    @SuppressWarnings("deprecated")
    public static class MyController {
        AsyncRestTemplate rt = new AsyncRestTemplate(new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1)));
//		AsyncRestTemplate rt = new AsyncRestTemplate();
//netty thread 갯수 default : 시스템 process core갯수 * 2

		@Autowired
		MyService myService;
        public static String URL1 = "http://localhost:8088/service?req={req}";
        public static String URL2 = "http://localhost:8088/service2?req={req}";


        @RequestMapping("/rest")
        public DeferredResult<String> rest(int idx) {
            DeferredResult<String> dr = new DeferredResult<>();
            ListenableFuture<ResponseEntity<String>> f1 = rt.getForEntity("http://localhost:8088/service?req={req}", String.class, "hello" + idx);
            f1.addCallback(s -> {

                ListenableFuture<ResponseEntity<String>> f2 = rt.getForEntity("http://localhost:8088/service2?req={req}", String.class, s.getBody());
                f2.addCallback(s2 -> {
                   ListenableFuture<String> f3 = myService.work(s2.getBody());
                   f3.addCallback(s3-> {
                   		dr.setResult(s3);
				   },e ->{
                   	    dr.setErrorResult(e.getMessage());
				   });

                }, e -> {
                    dr.setErrorResult(e.getMessage());
                });

            }, e -> {
                dr.setErrorResult(e.getMessage());
            });

            return dr;
        }

       @RequestMapping("/rest2")
       public DeferredResult<String> rest2(int idx) {
           DeferredResult<String> dr = new DeferredResult<>();
            Completion.from(rt.getForEntity(URL1, String.class, "hh" + idx))
                    .andApply(s-> rt.getForEntity(URL2, String.class, s.getBody()))
                    .andApply(s-> myService.work(s.getBody()))
                    .andError(e -> dr.setErrorResult(e.toString()))
                    .andAccept(s-> dr.setResult(s));

            return dr;
       }


    }

    public static class AcceptCompletion<S> extends Completion<S,Void>{
        Consumer<S> con;
        public AcceptCompletion(Consumer<S> con) {
            this.con = con;
        }

        @Override
        void run(S value) {
            con.accept(value);
        }
    }

    public static class ErrorCompletion<T> extends Completion<T,T>{
        Consumer<Throwable> econ;
        public ErrorCompletion(Consumer<Throwable> econ) {
            this.econ = econ;
        }

        @Override
        void run(T value) {
            if(next != null) next.run(value);
        }

        @Override
        void error(Throwable e) {
            econ.accept(e);
        }
    }

    public static class ApplyCompletion<S,T> extends Completion<S,T>{
        Function<S, ListenableFuture<T>> fn;
        public ApplyCompletion(Function<S, ListenableFuture<T>> fn) {
            this.fn = fn;
        }

        @Override
        void run(S value) {
            ListenableFuture<T> lf =  fn.apply(value);
            lf.addCallback(s->success(s), e->error(e));
        }
    }

    public static class Completion<S,T>{

        Completion next;

        public void andAccept(Consumer<T> con){
            Completion<T,Void> c = new AcceptCompletion<>(con);
            this.next = c;

        }

        public Completion<T,T> andError(Consumer<Throwable> econ){
            Completion<T,T> c = new ErrorCompletion<>(econ);
            this.next = c;
            return c;
        }

        public  <V> Completion<T,V> andApply(Function<T, ListenableFuture<V>> fn){
            Completion<T,V> c = new ApplyCompletion<>(fn);
            this.next = c;
            return c;
        }

        public static <S,T> Completion<S,T>  from(ListenableFuture<T> lf) {
            Completion<S,T> completion = new Completion<>();
            lf.addCallback(completion::success, completion::error);
            return completion;
        }

        void error(Throwable e) {
            if(next != null) next.error(e);
        }

        void success(T s) {
            if(next != null) next.run(s);
        }

        void run(S value) {
         }

    }

    @Service
    public static class MyService {
		@Async
		public ListenableFuture<String> work(String req){
			return new AsyncResult<>(req + "/asyncwork");
		}

    }

    @Bean
	public ThreadPoolTaskExecutor myThreadPool(){
    	ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
    	tp.setCorePoolSize(1);
    	tp.setMaxPoolSize(1);
    	tp.setQueueCapacity(1);
    	tp.initialize();
    	return tp;
	}

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }

}
