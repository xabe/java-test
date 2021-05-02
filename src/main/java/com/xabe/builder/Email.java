package com.xabe.builder;

public class Email {

  // To Address. Multiple Address separated by ","
  private final String to;

  //From Address
  private final String from;

  // Subject of the email
  private final String subject;

  // Content of the email
  private final String content;

  // BCC optional
  private final String bcc;

  // CC Optional
  private final String cc;

  Email(final String to, final String from, final String subject, final String content, final String bcc, final String cc) {
    this.to = to;
    this.from = from;
    this.subject = subject;
    this.content = content;
    this.bcc = bcc;
    this.cc = cc;
  }

  public String getTo() {
    return this.to;
  }

  public String getFrom() {
    return this.from;
  }

  public String getSubject() {
    return this.subject;
  }

  public String getContent() {
    return this.content;
  }

  public String getBcc() {
    return this.bcc;
  }

  public String getCc() {
    return this.cc;
  }

  public static EmailFrom builder() {
    return new EmailBuilder();
  }
}
