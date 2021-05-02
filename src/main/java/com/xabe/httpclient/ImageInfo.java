package com.xabe.httpclient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class ImageInfo {

  private String date;

  protected String imagePath;

  private byte[] imageData;

  public abstract ImageInfo findImage(String body);

  public void setDate(final String date) {
    this.date = date;
  }

  public String getDate() {
    return this.date;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public ImageInfo setImageData(final byte[] imageData) {
    this.imageData = imageData;
    return this;
  }

  public byte[] getImageData() {
    return this.imageData;
  }

  public abstract String getUrlForDate(LocalDate date);
}

class DilbertImageInfo extends ImageInfo {

  @Override
  public DilbertImageInfo findImage(final String body) {
    this.imagePath = findProperty(body, "image");
    return this;
  }

  private static String findProperty(final String body, final String filter) {
    final String search = "meta name=\"twitter:" + filter + "\" content=\"";
    return body.lines().filter(line -> line.contains(search))
        .findFirst()
        .map(line -> line.replaceAll(".*" + search, ""))
        .map(line -> line.replaceAll("\".*", ""))
        .orElseThrow(() -> new IllegalStateException("Could not find \"" + filter + "\""));
  }

  @Override
  public String getUrlForDate(final LocalDate date) {
    return "https://dilbert.com/strip/" + DateTimeFormatter.ISO_DATE.format(date);
  }
}

class WikimediaImageInfo extends ImageInfo {

  @Override
  public WikimediaImageInfo findImage(final String body) {
    final Pattern pattern = Pattern.compile("<img\\s+alt=\"[^\"]+\"\\s+src=\"(?<src>[^\\\"]+)\"");
    final Matcher matcher = pattern.matcher(body);
    if (matcher.find()) {
      this.imagePath = matcher.group("src");
    } else {
      this.imagePath = null;
    }
    return this;
  }

  @Override
  public String getUrlForDate(final LocalDate date) {
    return "https://commons.wikimedia.org/wiki/Special:FeedItem/potd/" + DateTimeFormatter.BASIC_ISO_DATE.format(date) + "000000/en";
  }
}
