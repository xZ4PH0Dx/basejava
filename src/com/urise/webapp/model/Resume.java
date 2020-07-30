package com.urise.webapp.model;

/**
 * Initial resume class
 */
public class Resume {

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // Unique identifier
    String uuid;

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
