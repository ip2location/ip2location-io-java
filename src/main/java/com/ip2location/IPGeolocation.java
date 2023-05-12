package com.ip2location;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import com.google.gson.*;

/**
 * This class performs the lookup of geolocation data from an IP address by querying the IP2Location.io API.
 * <p>
 * Copyright (c) 2002-2023 IP2Location.com
 * <p>
 *
 * @author IP2Location.com
 * @version 1.0.0
 */

public class IPGeolocation {
    private static final String BASE_URL = "https://api.ip2location.io/";
    private static final String SOURCE = "sdk-java-iplio";
    private static final String FORMAT = "json";
    private static final String ERROR = "IPGeolocation lookup error.";
    private final Configuration configuration;

    /**
     * This constructor accepts the Configuration object and store it.
     *
     * @param config The Configuration object.
     */
    public IPGeolocation(Configuration config) {
        configuration = config;
    }

    /**
     * This function to query IP2Location.io geolocation data.
     *
     * @param ip IP Address you wish to query
     * @return IP2Location.io geolocation data
     * @throws Exception If parameters are incorrect or API call failed.
     */
    public JsonObject Lookup(String ip) throws Exception {
        return Lookup(ip, "");
    }

    /**
     * This function to query IP2Location.io geolocation data.
     *
     * @param ip       IP Address you wish to query
     * @param language The translation language
     * @return IP2Location.io geolocation data
     * @throws Exception If parameters are incorrect or API call failed.
     */
    public JsonObject Lookup(String ip, String language) throws Exception {
        String url = BASE_URL + "?format=" + FORMAT + "&source=" + SOURCE + "&source_version=" + configuration.getVersion() + "&key=" + configuration.getApiKey() + "&ip=" + URLEncoder.encode(ip, "UTF-8") + "&lang=" + URLEncoder.encode(language, "UTF-8");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.thenApply(HttpResponse::statusCode).get();
        if (statusCode == 200) {
            String rawJSON = response.thenApply(HttpResponse::body).get();
            return JsonParser.parseString(rawJSON).getAsJsonObject();
        } else if (statusCode == 400 || statusCode == 401) {
            String rawJSON = response.thenApply(HttpResponse::body).get();
            if (rawJSON.contains("error_message")) {
                throw new Exception(JsonParser.parseString(rawJSON).getAsJsonObject().getAsJsonObject("error").get("error_message").getAsString());
            }
        }
        throw new Exception(ERROR);
    }
}
