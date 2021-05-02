package com.xabe.event.bus;

import java.time.LocalDateTime;

public class RequestEvent {

  private final String name;

  private final LocalDateTime time;

  public RequestEvent(final String name, final LocalDateTime time) {

    this.name = name;
    this.time = time;
  }

  public LocalDateTime getTime() {
    return this.time;
  }

  public String getName() {
    return this.name;
  }

  public static RequestEvent of(final Request request) {
    return new RequestEvent(request.getName(), request.getTime());
  }

  public static RequestEvent of(final String event, final LocalDateTime now) {
    return new RequestEvent(event, now);
  }

  @Override
  public String toString() {
    return new StringBuilder("[RequesEvent : name :").append(this.getName()).append(" , time : ").append(this.getTime()).append(" ]").toString();
  }
}
