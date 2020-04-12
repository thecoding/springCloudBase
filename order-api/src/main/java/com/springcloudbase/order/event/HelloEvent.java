package com.springcloudbase.order.event;

import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;

/**
 * Created by Mirko on 2020/3/26.
 */
public class HelloEvent extends ApplicationEvent {

    private String name;
    private String createDate;

    public HelloEvent(Object source, String name, String createDate) {
        super(source);
        this.name = name;
        this.createDate = createDate;
    }

    public HelloEvent(Object source, String name) {
        super(source);
        this.name = name;
        this.createDate = LocalDate.now().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
