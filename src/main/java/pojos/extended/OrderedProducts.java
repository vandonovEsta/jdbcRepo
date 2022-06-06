package pojos.extended;

import pojos.orders.Order;
import pojos.orders.OrderedQuality;
import pojos.products.Product;

import java.util.List;
import java.util.Objects;

public class OrderedProducts {
    private List<CustomerOrders> customerOrders;
    private List<OrderedQuality> orderedQualities;
    private List<Product> products;

    public OrderedProducts(List<CustomerOrders> customerOrders,
                           List<OrderedQuality> orderedQualities,
                           List<Product> products) {

        this.customerOrders = customerOrders;
        this.orderedQualities = orderedQualities;
        this.products = products;

    }

    public List<CustomerOrders> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrders> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public List<OrderedQuality> getOrderedQualities() {
        return orderedQualities;
    }

    public void setOrderedQualities(List<OrderedQuality> orderedQualities) {
        this.orderedQualities = orderedQualities;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        String result = "";
        for (CustomerOrders customerOrder :
                customerOrders) {
            result += customerOrder.getCustomer();
            List<Order> orders = customerOrder.getOrders();
            for (Order order :
                    orders) {
                result += "\nOrder: " + order;
                for (OrderedQuality orderedQuality :
                        orderedQualities) {
                    if (Objects.equals(orderedQuality.getId(), order.getId())) {
                        for (Product product :
                                products) {
                            if (orderedQuality.getProductId() == product.getId().intValue()) {
                                result += "\nProduct: " + product;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
