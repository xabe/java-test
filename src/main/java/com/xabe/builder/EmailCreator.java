package com.xabe.builder;

public interface EmailCreator {

  Email build();

  EmailCreator withBCC(String bcc);

  EmailCreator setCC(String cc);
}
