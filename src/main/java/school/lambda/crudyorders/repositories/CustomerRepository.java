package school.lambda.crudyorders.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import school.lambda.crudyorders.models.Customer;
import school.lambda.crudyorders.views.OrderCount;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
List<Customer> findByCustnameContainsIgnoringCase(String subname);


    @Query(value = "SELECT c.custname name, count(ordnum) ordercount " +
            "FROM customers c LEFT JOIN orders o " +
            "ON c.custcode = o.custcode " +
            "GROUP BY c.custname " +
            "ORDER BY ordercount DESC",
            nativeQuery = true)
    List<OrderCount> findOrderCounts();
}
