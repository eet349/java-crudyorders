package school.lambda.crudyorders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import school.lambda.crudyorders.models.Order;
import school.lambda.crudyorders.services.OrderServices;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
//    http://localhost:2019/orders/order/7
    @Autowired
    OrderServices orderServices;
    @GetMapping(value = "/order/{ordnum}")
    public ResponseEntity<?> findOrderById(@PathVariable long ordnum) {
        Order rtnAgent = orderServices.findOrderByOrdnum(ordnum);

        return new ResponseEntity<>(rtnAgent, HttpStatus.OK);
    }

    //  POST /orders/order - adds a new order to an existing customer
    @PostMapping(value = "/order", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<?> addNewOrder(@RequestBody Order newOrder) {
        newOrder.setOrdnum(0);
        newOrder = orderServices.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();

        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(/*newOrder, */responseHeaders, HttpStatus.CREATED);
    }
    //  PUT /orders/order/{ordernum} - completely replaces the given order record
    @PutMapping(value = "/order/{ordnum}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateCompleteCustomer(@Valid @RequestBody Order updateOrder, @PathVariable long ordnum) {
        updateOrder.setOrdnum(ordnum);
        updateOrder = orderServices.save(updateOrder);

        return new ResponseEntity<>(/*updateOrder, */HttpStatus.OK);
    }
    //  DELETE /orders/order/{ordernum} - deletes the given order
    @DeleteMapping(value = "/order/{ordnum}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordnum) {
        orderServices.delete(ordnum);
        return new ResponseEntity<>(/*ordnum,*/ HttpStatus.OK);
    }
}
