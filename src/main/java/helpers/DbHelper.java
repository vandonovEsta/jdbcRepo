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

    private Connection connection;
    private PropertiesHelper propertiesHelper;

    private DbHelper() throws IOException, SQLException {
        propertiesHelper = new PropertiesHelper();
        createConnection();
    }

    public static DbHelper getInstance() {
        return instance;
    }

    //TODO: Change to private?
    private void createConnection() throws IOException, SQLException {
        try {

            connection = DriverManager.getConnection(propertiesHelper.readProperty("url"),
                    propertiesHelper.readProperty("user"), propertiesHelper.readProperty("password"));

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
