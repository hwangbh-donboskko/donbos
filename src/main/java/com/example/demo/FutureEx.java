package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.*;

@Slf4j
public class FutureEx {

    interface SuccessCallback {
        void onSuccess(String result);
    }
    interface ErrorCallBack{

        void onError(Throwable t);
    }

    public static class CallbackFutureTask extends FutureTask<String >{
        SuccessCallback sc;
        ErrorCallBack ec;

        public CallbackFutureTask(Callable<String> callable, SuccessCallback sc, ErrorCallBack ec) {
            super(callable);
            this.sc = Objects.requireNonNull(sc);
            this.ec = Objects.requireNonNull(ec);
        }

        @Override
        protected void done() {
            try {

                sc.onSuccess(get());


            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                ec.onError(e.getCause());
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        CallbackFutureTask f = new CallbackFutureTask(()-> {
            Thread.sleep(1000);
            if(1==1) throw new RuntimeException("Async Error");

            log.warn("Async");
            return "Hello";

        }, System.out::println , e -> System.out.println("Error:" + e.getMessage()));

        es.execute(f);
        es.shutdown();
        }
}
