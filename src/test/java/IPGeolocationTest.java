import com.google.gson.JsonObject;
import com.ip2location.Configuration;
import com.ip2location.IPGeolocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IPGeolocationTest {

    @Test
    void TestQuery() {
        Configuration config = new Configuration();
        String apiKey = "DUMMY";
        config.setApiKey(apiKey);
        IPGeolocation ipl = new IPGeolocation(config);
        assertThrows(Exception.class, () -> {
            JsonObject myObj = ipl.Lookup("8.8.8.8", "en");
        });
    }
}


