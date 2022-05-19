package jdbsProj;

import helpers.DbHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class DbConnectionTests {
    Connection connection;
    DbHelper dbHelper;

    @BeforeEach
    public void initAll() throws IOException, SQLException {
        dbHelper = DbHelper.getInstance();
        connection = dbHelper.getConnection();
    }

    @Test
    public void onlyOneConnectionIsReturned() throws SQLException, IOException {
        assertFalse(connection.isClosed());
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.close();
    }

}
