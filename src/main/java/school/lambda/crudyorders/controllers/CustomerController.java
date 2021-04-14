package school.lambda.crudyorders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.lambda.crudyorders.models.Customer;
import school.lambda.crudyorders.services.CustomerServices;
import school.lambda.crudyorders.views.OrderCount;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    CustomerServices customerServices;
//    http://localhost:2019/customers/orders
    @GetMapping(value = "/orders")
    public ResponseEntity<?> getCustomerOrders() {
        List<Customer> rtnList = customerServices.findAllCustomers();

        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{custcode}", produces = "application/json")
    public ResponseEntity<?> getCustomerById(@PathVariable long custcode) {
//    http://localhost:2019/customers/customer/7
//    http://localhost:2019/customers/customer/77
        Customer rtnCust = customerServices.findCustomerById(custcode);
        return new ResponseEntity<>(rtnCust, HttpStatus.OK);
    }

    @GetMapping(value = "/namelike/{subname}")
    public ResponseEntity<?> getCustomersLikeName(@PathVariable String subname) {
//    http://localhost:2019/customers/namelike/mes
//    http://localhost:2019/customers/namelike/cin
        List<Customer> rtnList = customerServices.findByCustnameLike(subname);

        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @GetMapping(value = "/orders/count")
    public ResponseEntity<?> getOrderCounts() {
//    http://localhost:2019/customers/orders/count
        List<OrderCount> rtnList = customerServices.getOrderCounts();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //  POST /customers/customer - Adds a new customer including any new orders
    
    //  PUT /customers/customer/{custcode} - completely replaces the customer record including associated orders with the provided data
    //  PATCH /customers/customer/{custcode} - updates customers with the new data. Only the new data is to be sent from the frontend client.
    //  DELETE /customers/customer/{custcode} - Deletes the given customer including any associated orders

}
