import com.google.gson.JsonObject;
import com.ip2location.Configuration;
import com.ip2location.DomainWhois;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DomainWhoisTest {

    @Test
    void TestQuery() {
        Configuration config = new Configuration();
        String apiKey = "DUMMY";
        config.setApiKey(apiKey);
        DomainWhois whois = new DomainWhois(config);
        assertThrows(Exception.class, () -> {
            JsonObject myObj = whois.Lookup("locaproxy.com");
        });
    }

    @Test
    void TestPunycode() {
        Configuration config = new Configuration();
        DomainWhois whois = new DomainWhois(config);
        assertEquals(whois.toPunycode("täst.de"), "xn--tst-qla.de");
    }

    @Test
    void TestNormalText() {
        Configuration config = new Configuration();
        DomainWhois whois = new DomainWhois(config);
        assertEquals(whois.toNormalText("xn--tst-qla.de"), "täst.de");
    }

    @Test
    void TestDomainName() {
        Configuration config = new Configuration();
        DomainWhois whois = new DomainWhois(config);
        assertEquals(whois.toDomainName("https://www.example.com/exe"), "example.com");
    }

    @Test
    void TestDomainExtension() {
        Configuration config = new Configuration();
        DomainWhois whois = new DomainWhois(config);
        assertEquals(whois.toDomainExtension("example.com"), ".com");
    }
}


