package school.lambda.crudyorders.services;

import school.lambda.crudyorders.models.Customer;
import school.lambda.crudyorders.views.OrderCount;

import java.util.List;

public interface CustomerServices {
    Customer save(Customer customer);
    List<Customer> findAllCustomers();
    Customer findCustomerById(long custcode);
    List<Customer> findByCustnameLike(String subname);
    List<OrderCount> getOrderCounts();
    void delete(long id);
    Customer update(Customer customer, long id);
}
