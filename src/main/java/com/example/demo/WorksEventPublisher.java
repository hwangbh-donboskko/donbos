package com.example.demo;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
@Getter
public class WorksEventPublisher extends ApplicationEvent {

    private final Works works;

    public WorksEventPublisher(Object source) {
        super(source);
        this.works = (Works) source;
    }
}
