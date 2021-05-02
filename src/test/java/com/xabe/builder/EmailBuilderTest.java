package com.xabe.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

public class EmailBuilderTest {

  public static final String FROM = "Test@gmail.com";

  public static final String TO = "mail@gmail.com";

  public static final String SUBJECT = "Test with only required Fields";

  public static final String CONTENT = " Required Field Test";

  public static final String BCC = "bcc@gmail.com";

  public static final String CC = "cc@gmail.com";

  @Test
  public void shouldCreateEmail() throws Exception {
    //Given

    //When
    final Email result = Email.builder().withFrom(FROM).withTo(TO)
        .withSubject(SUBJECT).withContent(CONTENT).build();

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.getFrom(), is(FROM));
    assertThat(result.getTo(), is(TO));
    assertThat(result.getSubject(), is(SUBJECT));
    assertThat(result.getContent(), is(CONTENT));

  }

  @Test
  public void shouldCreateEmailWithBccAndCC() throws Exception {
    //Given

    //When
    final Email result = Email.builder().withFrom(FROM).withTo(TO)
        .withSubject(SUBJECT).withContent(CONTENT).withBCC(BCC)
        .setCC(CC).build();

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.getFrom(), is(FROM));
    assertThat(result.getTo(), is(TO));
    assertThat(result.getSubject(), is(SUBJECT));
    assertThat(result.getContent(), is(CONTENT));
    assertThat(result.getBcc(), is(BCC));
    assertThat(result.getCc(), is(CC));

  }
}
