package com.example.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import sun.applet.AppletListener;

@Component
public class WorksListener implements ApplicationListener<WorksEventPublisher> {

    @Override
    public void onApplicationEvent(WorksEventPublisher worksEventPublisher) {
        System.out.println("-----------------");
        System.out.println(worksEventPublisher.getWorks().getTitle());
        System.out.println("------------------");
    }
}
