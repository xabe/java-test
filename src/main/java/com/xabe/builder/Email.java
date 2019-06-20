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


    Email(String to, String from, String subject, String content, String bcc, String cc) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.content = content;
        this.bcc = bcc;
        this.cc = cc;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getBcc() {
        return bcc;
    }

    public String getCc() {
        return cc;
    }

    public static EmailFrom builder() {
        return new EmailBuilder();
    }
}
