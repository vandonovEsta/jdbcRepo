package constants;

public class SqlQueries {
    public static final String SELECT_ALL_FROM_CUSTOMERS_GET_ONLY_FIRST_ROW
            = "SELECT * FROM customers ORDER BY customer_id LIMIT 1";
}
