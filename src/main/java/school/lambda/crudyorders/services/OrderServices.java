package school.lambda.crudyorders.services;

import school.lambda.crudyorders.models.Order;

public interface OrderServices {
    Order save(Order order);
    Order findOrderByOrdnum(long ordnum);
    void delete(long id);
//    Order update(Order order, long id);
}
