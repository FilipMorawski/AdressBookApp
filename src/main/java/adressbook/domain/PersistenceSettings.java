package adressbook.domain;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Component
public class PersistenceSettings {
    private final String urlBeginning = "jdbc:mysql://";
    private final String persistenceUnit = "adressBook";
    private final String driver = "com.mysql.jdbc.Driver";
    private String url;
    private String database;
    private Map<String, String> persistenceMap;

    public EntityManagerFactory setPersistenceSettings(HashMap<String, String> receivedData) {
        persistenceMap = new HashMap<String, String>();
        url = receivedData.get("url");
        database = receivedData.get("database");
        String databaseUrl = urlBeginning + url + "/" + database;
        persistenceMap.put("javax.persistence.jdbc.driver", driver);
        persistenceMap.put("javax.persistence.jdbc.user", receivedData.get("user"));
        persistenceMap.put("javax.persistence.jdbc.password", receivedData.get("password"));
        persistenceMap.put("javax.persistence.jdbc.url", databaseUrl);

        return Persistence.createEntityManagerFactory(persistenceUnit, persistenceMap);
    }
}
