package com.xabe.autoclose;

public class NotAutoclosable {

    private boolean opened;

    public NotAutoclosable() {
        opened = true;
    }

    public void dispose() {
        opened = false;
    }

    public boolean isOpened() {
        return opened;
    }
}
