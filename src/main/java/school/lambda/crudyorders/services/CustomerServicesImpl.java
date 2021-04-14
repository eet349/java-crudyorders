package school.lambda.crudyorders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.lambda.crudyorders.models.Customer;
import school.lambda.crudyorders.repositories.CustomerRepository;
import school.lambda.crudyorders.views.OrderCount;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices{
    @Autowired
    private CustomerRepository custrepo;
    @Transactional
    @Override
    public Customer save(Customer customer) {
        return custrepo.save(customer);
    }
    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> list = new ArrayList<>();
        custrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
    @Override
    public Customer findCustomerById(long custid) {
        return custrepo.findById(custid).orElseThrow(() -> new EntityNotFoundException("Customer id " + custid + " Not Found!"));
    }

    @Override
    public List<Customer> findByCustnameLike(String subname) {
        List<Customer> rtnList = custrepo.findByCustnameContainsIgnoringCase(subname);
        return rtnList;
    }
    @Override
    public List<OrderCount> getOrderCounts() {
        List<OrderCount> rtnList = custrepo.findOrderCounts();
        return rtnList;
    }

}
