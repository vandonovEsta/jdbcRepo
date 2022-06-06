package jdbsProj;

import helpers.SingleConnectionHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;


public class DbConnectionTests {
    private static Connection connection;
    private static SingleConnectionHelper singleConnectionHelper;

    @BeforeAll
    public static void init() throws IOException, SQLException {
        System.out.println("Before All is executed");
        singleConnectionHelper = SingleConnectionHelper.getInstance();
        connection = singleConnectionHelper.getConnection();
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        System.out.println("After all is executed");
        connection.close();
    }

    @Test
    public void onlyOneConnectionIsReturned() throws SQLException, IOException {

        assertFalse(connection.isClosed());
        System.out.println("Test is executed");

    }

}
