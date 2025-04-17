import com.google.gson.JsonObject;
import com.ip2location.Configuration;
import com.ip2location.HostedDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HostedDomainTest {

    @Test
    void TestQuery() {
        Configuration config = new Configuration();
        String apiKey = "DUMMY";
        config.setApiKey(apiKey);
        HostedDomain hd = new HostedDomain(config);
        assertThrows(Exception.class, () -> {
            JsonObject myObj = hd.Lookup("8.8.8.8");
        });
    }
}


