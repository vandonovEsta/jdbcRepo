package helpers;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class DataBaseHelperTests {

    @Test
    public void createConnectionTest() throws IOException {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        try (Connection connection = dataBaseHelper.makeConnection()) {
            assertFalse("Connection should be open in try clause", connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
