# Quickstart

## Pre-requisite
IntelliJ IDEA

## Dependencies

This module requires API key to function. You may sign up for a free API key at <https://www.ip2location.io/pricing>.

## Installation

https://central.sonatype.com/artifact/com.ip2location/ip2location-io-java/

## Sample Codes

### Lookup IP Address Geolocation Data

You can make a geolocation data lookup for an IP address as below:

```java
// Configures IP2Location.io API key
Configuration config = new Configuration();
String apiKey = "YOUR_API_KEY";
config.setApiKey(apiKey);
IPGeolocation ipl = new IPGeolocation(config);

// Lookup ip address geolocation data
JsonObject myObj = ipl.Lookup("8.8.8.8", "en"); // the language parameter is only available for Plus and Security plans
System.out.println(myObj);
```

### Lookup Domain Information

You can lookup domain information as below:

```java
// Configures IP2Location.io API key
Configuration config = new Configuration();
String apiKey = "YOUR_API_KEY";
config.setApiKey(apiKey);
DomainWhois whois = new DomainWhois(config);

// Lookup domain information
JsonObject myObj = whois.Lookup("locaproxy.com");
System.out.println(myObj);
```

### Convert Normal Text to Punycode

You can convert an international domain name to Punycode as below:

```java
Configuration config = new Configuration();
DomainWhois whois = new DomainWhois(config);

// Convert normal text to punycode
System.out.println(whois.toPunycode("täst.de"));
```

### Convert Punycode to Normal Text

You can convert a Punycode to international domain name as below:

```java
Configuration config = new Configuration();
DomainWhois whois = new DomainWhois(config);

// Convert punycode to normal text
System.out.println(whois.toNormalText("xn--tst-qla.de"));
```

### Get Domain Name

You can extract the domain name from an url as below:

```java
Configuration config = new Configuration();
DomainWhois whois = new DomainWhois(config);

// Get domain name from URL
System.out.println(whois.toDomainName("https://www.example.com/exe"));
```

### Get Domain Extension

You can extract the domain extension from a domain name or url as below:

```java
Configuration config = new Configuration();
DomainWhois whois = new DomainWhois(config);

// Get domain extension (gTLD or ccTLD) from URL or domain name
System.out.println(whois.toDomainExtension("example.com"));
```