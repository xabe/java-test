package com.xabe.builder;

public class EmailBuilder implements EmailFrom, EmailTo, EmailSubject, EmailContent, EmailCreator {

  private String from;

  private String to;

  private String subject;

  private String content;

  private String cc;

  private String bcc;

  @Override
  public EmailTo withFrom(String from) {
    this.from = from;
    return this;
  }

  @Override
  public EmailSubject withTo(String to) {
    this.to = to;
    return this;
  }

  @Override
  public EmailContent withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  @Override
  public EmailCreator withContent(String content) {
    this.content = content;
    return this;
  }

  @Override
  public EmailCreator withBCC(String bcc) {
    this.bcc = bcc;
    return this;
  }

  @Override
  public EmailCreator setCC(String cc) {
    this.cc = cc;
    return this;
  }

  @Override
  public Email build() {
    return new Email(to, from, subject, content, bcc, cc);
  }
}
