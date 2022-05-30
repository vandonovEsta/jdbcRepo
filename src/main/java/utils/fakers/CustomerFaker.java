package utils.fakers;

import com.github.javafaker.Faker;
import pojos.customers.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerFaker {
    public static Customer createFakeCustomer() {
        Faker faker = new Faker();
        Customer customer;
        Integer customerId = faker.number().randomDigit();
        String customerName = faker.funnyName().name();
        String email = faker.letterify("?????@??.###");
        String phone = faker.numerify("+359#########");
        int age = Integer.valueOf(faker.numerify("##"));
        boolean gdpr = faker.bool().bool();
        boolean customerProfileStatus = faker.bool().bool();
        Date dateTimeCreated = faker.date().past(90, TimeUnit.DAYS);
        Date dateTimeDeactivated;
        String reasonForDeactivation;

        if (customerProfileStatus) {
            dateTimeDeactivated = null;
            reasonForDeactivation = null;
        } else {
            dateTimeDeactivated = new Date();
            reasonForDeactivation = faker.chuckNorris().fact();
        }
        String notes = faker.backToTheFuture().quote();
        int address_id = 0;
        int order_id = 0;

        customer = new Customer(
                customerId, customerName, email,
                phone, age, gdpr, customerProfileStatus,
                dateTimeCreated, dateTimeDeactivated, reasonForDeactivation,
                notes, address_id, order_id
        );

        return customer;
    }

    public static List<Customer> createFakeCustomers(int numberOfCustomer) {
        List<Customer> fakeCustomers = new ArrayList<>();
        for (int i = 0; i < numberOfCustomer; i++) {
            fakeCustomers.add(createFakeCustomer());
        }
        return fakeCustomers;
    }

    public static List<Customer> createFakeCustomers() {
        int numberOfCustomers = Faker.instance().number().numberBetween(2, 15);
        return createFakeCustomers(numberOfCustomers);
    }
}
