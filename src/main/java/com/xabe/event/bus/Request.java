package com.xabe.event.bus;

import java.time.LocalDateTime;

public class Request {

    private final String name;
    private final LocalDateTime time;

    public Request(String name, LocalDateTime time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public static Request of(String name, LocalDateTime time) {
        return new Request(name,time);
    }
}
