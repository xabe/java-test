package com.xabe.autoclose;

public class NotAutoclosable {

  private boolean opened;

  public NotAutoclosable() {
    this.opened = true;
  }

  public void dispose() {
    this.opened = false;
  }

  public boolean isOpened() {
    return this.opened;
  }
}
