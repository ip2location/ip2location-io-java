# IP2Location.io Java SDK — IP Geolocation, Domain WHOIS & Hosted Domain Lookup

[![Maven Central](https://img.shields.io/maven-central/v/com.ip2location/ip2location-io-java.svg)](https://central.sonatype.com/artifact/com.ip2location/ip2location-io-java)

**IP2Location.io Java SDK** is a Java library for retrieving **IPv4 and IPv6 geolocation, network and IP intelligence data** through the [IP2Location.io API](https://www.ip2location.io/). It also supports **domain WHOIS lookup** and **hosted domain lookup**. Use the SDK to enrich Java applications with IP and domain information without constructing REST API requests manually.

**Quick links:** [Get an API key](https://www.ip2location.io/pricing) · [Developer documentation](https://ip2location-io-java.readthedocs.io/en/latest/index.html) · [API reference](https://ip2location-io-java.readthedocs.io/en/latest/code.html) · [Maven Central package](https://central.sonatype.com/artifact/com.ip2location/ip2location-io-java)

## Features

- **IPv4 and IPv6 geolocation:** Look up country, region, district, city, ZIP/postal code, latitude, longitude and time zone.
- **Network and ASN information:** Retrieve available ASN, autonomous system, ISP, domain, network speed, usage type and related attributes.
- **Time zone and localization data:** Access available time zone/DST information and request translated location names (Plus and Security plans).
- **Proxy and security signals:** Access available proxy, VPN, Tor, data center, crawler and other security indicators, including fraud score where supported by your plan.
- **Additional IP intelligence:** Retrieve eligible mobile carrier (MCC/MNC), weather/elevation, country metadata and advertisement category fields.
- **Domain WHOIS lookup:** Retrieve available registrar, registration dates, domain statuses, nameservers and contact information.
- **Internationalized-domain and URL utilities:** Convert domain names to and from Punycode, extract domain names from URLs and identify domain extensions.
- **Hosted domains:** Retrieve domain names hosted on an IPv4 or IPv6 address, with pagination support.

> **Plan availability:** Data fields and security signals vary by API plan and queried address. Refer to the [pricing page](https://www.ip2location.io/pricing) and [Java API reference](https://ip2location-io-java.readthedocs.io/en/latest/code.html) for details.

## SDK classes

The [Java SDK API reference](https://ip2location-io-java.readthedocs.io/en/latest/code.html) documents these four classes:

| Class | Description |
| --- | --- |
| [`Configuration`](https://ip2location-io-java.readthedocs.io/en/latest/code.html#configuration-class) | Configures the API key for SDK lookups using `setApiKey()`. |
| [`IPGeolocation`](https://ip2location-io-java.readthedocs.io/en/latest/code.html#ipgeolocation-class) | Performs IPv4/IPv6 geolocation and available network/security lookups. |
| [`DomainWhois`](https://ip2location-io-java.readthedocs.io/en/latest/code.html#domainwhois-class) | Performs domain WHOIS lookups and provides Punycode/domain utilities. |
| [`HostedDomain`](https://ip2location-io-java.readthedocs.io/en/latest/code.html#hosteddomain-class) | Retrieves domains hosted on an IP address, with optional pagination. |

**Method naming:** The official Java SDK uses a capital **`Lookup`** method for the three API services (not `lookup`).

## API access and query limits

IP2Location.io offers keyless geolocation requests through its REST API and a Free plan with an API key:

| API service | Keyless (no API key) | Free plan (with API key) |
| --- | --- | --- |
| IP Geolocation API | **1,000 queries/day** | **50,000 queries/month** |
| Domain WHOIS API | — | **500 queries/month** |
| Hosted Domain API | — | **50 queries/month** |

- **Keyless access:** Limited geolocation requests may be made directly through the [IP Geolocation REST API](https://www.ip2location.io/ip2location-documentation), with the daily allowance resetting at **00:00 UTC**. The Java SDK examples below use an API key; keyless access is not described as an SDK configuration mode.
- **Free plan:** Register for an API key to use the published monthly allowances, subject to the respective service quotas.
- **Attribution:** Keyless and Free-plan use requires attribution; see the [pricing page](https://www.ip2location.io/pricing) for applicable conditions.
- **Paid plans:** Refer to the [pricing page](https://www.ip2location.io/pricing) for current higher query limits, additional attributes and pricing.

For API authentication and limits, see the [IP Geolocation API documentation](https://www.ip2location.io/ip2location-documentation).

## Requirements

- **Java 11 or later.** The repository's Maven compiler settings target Java 11.
- A Java development environment such as [IntelliJ IDEA](https://www.jetbrains.com/idea/) (named in the official quickstart), or another IDE/editor.
- [Apache Maven](https://maven.apache.org/) for the Maven installation shown below, or another compatible build tool.
- An [IP2Location.io API key](https://www.ip2location.io/pricing) for the SDK examples.
- Network access to the IP2Location.io API services.

The published Maven artifact depends on Google's **Gson** library for `JsonObject` responses; Maven resolves this dependency automatically.

## Installation

### 1. Add the Maven dependency

Add the following to your project's `pom.xml`:

```xml
<dependency>
    <groupId>com.ip2location</groupId>
    <artifactId>ip2location-io-java</artifactId>
    <version>1.1.1</version>
</dependency>
```

The version is based on the current [Maven Central artifact](https://central.sonatype.com/artifact/com.ip2location/ip2location-io-java) and the repository's [`pom.xml`](https://github.com/ip2location/ip2location-io-java/blob/main/pom.xml). To download project dependencies, run:

```bash
mvn dependency:resolve
```

### 2. Set your API key

Set the API key in an environment variable instead of committing it to source code.

**Linux / macOS terminal:**

```bash
export IP2LOCATION_API_KEY='YOUR_API_KEY'
```

**Windows Command Prompt:**

```bat
set IP2LOCATION_API_KEY=YOUR_API_KEY
```

The official Java quickstart sets the API key with `config.setApiKey("YOUR_API_KEY")`. The examples in this README use `System.getenv()` for safer application configuration; this is an adaptation, not a different SDK method.

## Quickstart: IP geolocation lookup

Create `src/main/java/App.java` in a Maven project:

```java
import com.google.gson.JsonObject;
import com.ip2location.Configuration;
import com.ip2location.IPGeolocation;

public class App {
    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("IP2LOCATION_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Set the IP2LOCATION_API_KEY environment variable.");
            return;
        }

        Configuration config = new Configuration();
        config.setApiKey(apiKey);
        IPGeolocation geo = new IPGeolocation(config);

        // The optional language argument is demonstrated in the examples below.
        JsonObject result = geo.Lookup("8.8.8.8", "en");
        System.out.println(result);
    }
}
```

This follows the official quickstart’s `Lookup("8.8.8.8", "en")` call. The second argument is a language code; translated location names are supported on **Plus and Security** plans.

Run `App` from your IDE or a configured Maven application runner. The code prints the API's JSON response. Available fields depend on your plan and the queried address. See the [Java geolocation field reference](https://ip2location-io-java.readthedocs.io/en/latest/code.html#ipgeolocation-class).

## Working with JSON responses

The SDK returns `com.google.gson.JsonObject` for its API lookups. You can inspect returned keys and check whether optional values exist before reading them.

```java
JsonObject result = geo.Lookup("8.8.8.8", "en");
if (result.has("country_name") && !result.get("country_name").isJsonNull()) {
    System.out.println("Country: " + result.get("country_name").getAsString());
}
```

The remaining examples assume you have initialized `config` as in the quickstart, imported `com.google.gson.JsonObject` and the relevant SDK classes (`IPGeolocation`, `DomainWhois`, or `HostedDomain`). Place individual snippets in a method that declares `throws Exception`, as shown in the quickstart.

## More usage examples

### Request translated location names

The official [Java quickstart](https://ip2location-io-java.readthedocs.io/en/latest/quickstart.html) demonstrates the language argument to `Lookup`. Translated continent, country, region and city names require a **Plus or Security** plan.

```java
IPGeolocation geo = new IPGeolocation(config);
JsonObject result = geo.Lookup("8.8.8.8", "es");
System.out.println(result);
```

### Inspect ASN and ISP information

*Additional example using documented geolocation response fields.* Available attributes differ by plan.

```java
IPGeolocation geo = new IPGeolocation(config);
JsonObject result = geo.Lookup("8.8.8.8", "en");

if (result.has("asn") && !result.get("asn").isJsonNull()) {
    System.out.println("ASN: " + result.get("asn").getAsString());
}
if (result.has("isp") && !result.get("isp").isJsonNull()) {
    System.out.println("ISP: " + result.get("isp").getAsString());
}
```

### Inspect available proxy indicators

*Additional example using documented geolocation response fields.* Security data is plan-dependent and may be missing or null.

```java
IPGeolocation geo = new IPGeolocation(config);
JsonObject result = geo.Lookup("8.8.8.8", "en");

if (result.has("is_proxy") && !result.get("is_proxy").isJsonNull()) {
    System.out.println("Is proxy: " + result.get("is_proxy").getAsBoolean());
}
if (result.has("proxy") && result.get("proxy").isJsonObject()) {
    JsonObject proxy = result.getAsJsonObject("proxy");
    if (proxy.has("is_vpn") && !proxy.get("is_vpn").isJsonNull()) {
        System.out.println("VPN: " + proxy.get("is_vpn").getAsBoolean());
    }
    if (proxy.has("is_tor") && !proxy.get("is_tor").isJsonNull()) {
        System.out.println("Tor: " + proxy.get("is_tor").getAsBoolean());
    }
}
```

### Look up domain WHOIS information

The following uses the officially documented `DomainWhois.Lookup(domain)` method.

```java
DomainWhois whois = new DomainWhois(config);
JsonObject result = whois.Lookup("locaproxy.com");
System.out.println(result);
```

Responses may include creation and expiration dates, registrar details, statuses and nameservers. Some registration contact fields may be redacted or unavailable. See the [DomainWhois API reference](https://ip2location-io-java.readthedocs.io/en/latest/code.html#domainwhois-class).

### Use domain and Punycode utilities

The official quickstart documents these four `DomainWhois` helper methods:

```java
DomainWhois whois = new DomainWhois(config);

// Convert internationalized domain names.
System.out.println(whois.toPunycode("täst.de"));
System.out.println(whois.toNormalText("xn--tst-qla.de"));

// Extract domain information.
System.out.println(whois.toDomainName("https://www.example.com/exe"));
System.out.println(whois.toDomainExtension("example.com"));
```

These are helper methods rather than WHOIS network lookups.

### Find domains hosted on an IP address

The following follows the official hosted-domain quickstart:

```java
HostedDomain hostedDomain = new HostedDomain(config);
JsonObject result = hostedDomain.Lookup("8.8.8.8");
System.out.println(result);
```

The [API reference](https://ip2location-io-java.readthedocs.io/en/latest/code.html#hosteddomain-class) also documents an optional page number:

```java
JsonObject nextPage = hostedDomain.Lookup("8.8.8.8", 2);
System.out.println(nextPage);
```

Hosted-domain responses can include `total_domains`, `page`, `per_page`, `total_pages` and `domains`. Use the pagination metadata to decide whether to request more pages.

## Java and Maven troubleshooting

- **Dependency not resolved:** Confirm that the Maven dependency uses `com.ip2location:ip2location-io-java` (not the separate, BIN-database-based `ip2location-java` artifact) and refresh Maven dependencies.
- **Java version mismatch:** Check `java -version` and `mvn -version`. The SDK repository targets Java 11.
- **Missing imports:** Import the SDK classes from `com.ip2location` and `JsonObject` from `com.google.gson`.
- **API key or request errors:** Verify that `IP2LOCATION_API_KEY` is available in the process running your application and that the account has sufficient quota for the requested service.
- **Missing JSON fields:** Check the response and your plan before accessing optional network, proxy or WHOIS properties.

## Common use cases

- **Website and application localization:** Suggest region-appropriate content, language or currency based on approximate IP location.
- **Traffic analytics:** Enrich events with available country, city, ASN and network attributes.
- **Security review:** Use available proxy, VPN, Tor and fraud signals as supplementary context, not as the sole basis for a decision.
- **Domain research:** Inspect available registrar, registration dates, nameserver and status data from WHOIS responses.
- **Hosting and network investigation:** Identify domain names hosted on an IP address and examine associated network information.

## Documentation and support

- [Java SDK quickstart and sample codes](https://ip2location-io-java.readthedocs.io/en/latest/quickstart.html)
- [Java API reference and response fields](https://ip2location-io-java.readthedocs.io/en/latest/code.html)
- [Official Java SDK GitHub repository](https://github.com/ip2location/ip2location-io-java)
- [Maven Central package](https://central.sonatype.com/artifact/com.ip2location/ip2location-io-java)
- [IP2Location.io API plans and query limits](https://www.ip2location.io/pricing)
- [IP Geolocation API documentation](https://www.ip2location.io/ip2location-documentation)
- [Report a Java SDK issue or request a feature](https://github.com/ip2location/ip2location-io-java/issues)
- [API and account support](https://www.ip2location.io/contact-us)

## License

Licensed under the **MIT License**. See [LICENSE](./LICENSE).
