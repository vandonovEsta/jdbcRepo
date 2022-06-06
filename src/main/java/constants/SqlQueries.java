package constants;

public class SqlQueries {

    //SELECT queries
    public static final String SELECT_ALL_FROM_CUSTOMERS_GET_ONLY_FIRST_ROW
            = "SELECT * FROM customers ORDER BY customer_id LIMIT 1";
    public static final String SELECT_ALL_FROM_CUSTOMERS = "SELECT * FROM customers ORDER BY customer_id";

    public static final String SELECT_ALL = "SELECT * FROM ";

    public static final String COUNT_BY = "SELECT COUNT(%s) FROM ";

    public static final String SELECT_LAST_ID = "SELECT MAX(%s) FROM %s";

    public static final String SELECT_IN = "SELECT * FROM %s WHERE %s IN (%s)";

    public static final String SELECT_MAX_PK = "SELECT %s FROM %s ORDER BY %s DESC LIMIT 1";

    //WHERE queries
    public static final String WHERE_IS_EQUAL_TO = " WHERE %s = '%s'";

    //DELETE queries
    public static final String DELETE_BY = "DELETE FROM %s WHERE %s = %s";

    //TRUNCATE queries
    public static final String DELETE_ALL = "TRUNCATE TABLE %s CASCADE";

    //INSERT queries
    public static final String INSERT_INTO_TABLE = "INSERT INTO %s (%s) VALUES (%s)";
}
