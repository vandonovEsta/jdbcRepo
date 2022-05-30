package helpers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHelper {
    private String url;
    private String user;
    private String password;
    private PropertiesHelper propertiesHelper;

    public DataBaseHelper() throws IOException {
        propertiesHelper = new PropertiesHelper();
        url = propertiesHelper.readProperty("url");
        user = propertiesHelper.readProperty("user");
        password = propertiesHelper.readProperty("password");
    }

    public Connection makeConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
