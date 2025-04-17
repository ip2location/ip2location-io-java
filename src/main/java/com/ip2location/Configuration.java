package com.ip2location;

/**
 * This class gets and sets the IP2Location.io API key that's being used.
 * <p>
 * Copyright (c) 2002-2025 IP2Location.com
 * <p>
 *
 * @author IP2Location.com
 * @version 1.1.0
 */

public class Configuration {
    private static final String VERSION = "1.1.0";
    private String apiKey = "";

    public String getVersion() {
        return VERSION;
    }

    public void setApiKey(String key) {
        this.apiKey = key;
    }

    public String getApiKey() {
        return this.apiKey;
    }
}
