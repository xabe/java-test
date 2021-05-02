package com.xabe.event.bus;

import java.time.LocalDateTime;

public class Request {

  private final String name;

  private final LocalDateTime time;

  public Request(final String name, final LocalDateTime time) {
    this.name = name;
    this.time = time;
  }

  public String getName() {
    return this.name;
  }

  public LocalDateTime getTime() {
    return this.time;
  }

  public static Request of(final String name, final LocalDateTime time) {
    return new Request(name, time);
  }
}
