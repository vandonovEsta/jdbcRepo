package helpers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private static DbHelper instance;

    static {
        try {
            instance = new DbHelper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String url;
    private String user;
    private String password;
    private Connection connection;
    private PropertiesHelper propertiesHelper;

    private DbHelper() throws IOException, SQLException {
        propertiesHelper = new PropertiesHelper();
        createConnection();
    }

    public static DbHelper getInstance() {
        return instance;
    }

    private void prepareConnectionDetails() throws IOException {
        url = propertiesHelper.readProperty("url");
        user = propertiesHelper.readProperty("user");
        password = propertiesHelper.readProperty("password");
    }
    
    private void createConnection() throws IOException, SQLException {
        try {
            prepareConnectionDetails();
            connection = DriverManager.getConnection(url, user, password);

        } catch (IOException | SQLException e) {
            throw e;
        }
    }

    public Connection getConnection() throws SQLException, IOException {
        if (connection.isClosed()) {
            createConnection();
        }
        return connection;
    }
}
