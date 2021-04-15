package school.lambda.crudyorders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.lambda.crudyorders.models.Order;
import school.lambda.crudyorders.models.Payment;
import school.lambda.crudyorders.repositories.OrderRepository;
import school.lambda.crudyorders.repositories.PaymentRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private OrderRepository orderrepo;

    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();

        if(order.getOrdnum() != 0) {
            findOrderByOrdnum(order.getOrdnum());
            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());

        //  Many to one - customer
        //  newMenu.setRestaurant(restaurantServices.findRestaurantById(menu.getRestaurant().getRestaurantid())); // the the one from our db
        newOrder.setCustomer(customerServices.findCustomerById(order.getCustomer().getCustcode()));
        //  Many to Many - payments
        newOrder.getPayments().clear();
        for (Payment p : order.getPayments()) {
            Payment newPay = paymentRepository.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));
            newOrder.getPayments().add(newPay);
        }


        return orderrepo.save(newOrder);
    }
    @Override
    public Order findOrderByOrdnum(long ordnum){
        return orderrepo.findById(ordnum).orElseThrow(() -> new EntityNotFoundException("Order number " + ordnum + " Not Found!" ));
    }
    @Transactional
    @Override
    public void delete(long id){
        if(orderrepo.findById(id).isPresent()) {
            orderrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Order " + id + " Not Found!");
        }
    }

}
