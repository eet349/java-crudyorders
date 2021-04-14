package school.lambda.crudyorders.repositories;

import org.springframework.data.repository.CrudRepository;
import school.lambda.crudyorders.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
//
//    @Query(value = "SELECT c.custname name, count(ordnum) ordercount " +
//            "FROM customers c LEFT JOIN orders o " +
//            "ON c.custcode = o.custcode " +
//            "GROUP BY c.custname " +
//            "ORDER BY ordercount DESC",
//            nativeQuery = true)
//    List<OrderCount> findOrderCounts();
}
