package com.xabe.event.bus;

import java.time.LocalDateTime;

public class RequestEvent {
    private final String name;
    private final LocalDateTime time;

    public RequestEvent(String name, LocalDateTime time) {

        this.name = name;
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public static RequestEvent of(Request request) {
        return new RequestEvent(request.getName(),request.getTime());
    }
    public static RequestEvent of(String event, LocalDateTime now) {
        return new RequestEvent(event,now);
    }

    @Override
    public String toString() {
        return new StringBuilder("[RequesEvent : name :").append(getName()).append(" , time : ").append(getTime()).append(" ]").toString();
    }
}
