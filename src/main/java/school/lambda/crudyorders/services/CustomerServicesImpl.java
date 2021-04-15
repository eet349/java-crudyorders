package school.lambda.crudyorders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.lambda.crudyorders.models.Customer;
import school.lambda.crudyorders.models.Order;
import school.lambda.crudyorders.repositories.CustomerRepository;
import school.lambda.crudyorders.views.OrderCount;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices{
    @Autowired
    private AgentServices agentServices;
    @Autowired
    private CustomerRepository custrepo;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();

        if(customer.getCustcode() != 0) {
            findCustomerById(customer.getCustcode());
            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());

        //  agent - Many to one
        newCustomer.setAgent(agentServices.findAgentById(customer.getAgent().getAgentcode()));
//        newMenu.setRestaurant(restaurantServices.findRestaurantById(menu.getRestaurant().getRestaurantid())); // the the one from our db


        //  orders - One to many
        newCustomer.getOrders().clear();

        for(Order o : newCustomer.getOrders()) {
            Order newOrder = new Order();

            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setOrdamount(o.getAdvanceamount());
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setCustomer(newCustomer);
            newOrder.setPayments(o.getPayments());

            newCustomer.getOrders().add(newOrder);
        }

        return custrepo.save(newCustomer);
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
    @Transactional
    @Override
    public void delete(long id) {
        if(custrepo.findById(id).isPresent()) {
            custrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer " + id + " Not Found!");
        }

    }

    @Override
    public Customer update(Customer customer, long id) {
        Customer currentCustomer = new Customer();

        if(customer.getCustname() != null){
            currentCustomer.setCustname(customer.getCustname());
        }
        if(customer.getCustcity() != null){
        currentCustomer.setCustcity(customer.getCustcity());
        }
        if(customer.getWorkingarea() != null){
        currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if(customer.getCustcountry() != null){
        currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if(customer.getGrade() != null){
        currentCustomer.setGrade(customer.getGrade());
        }
        if(customer.hasvalueforopeningamt){
        currentCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if(customer.hasvalueforreceiveamt){
        currentCustomer.setReceiveamt(customer.getReceiveamt());
        }
        if(customer.hasvalueforpaymentamt){
        currentCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if(customer.hasvalueforoutstandingamt){
        currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if(customer.getPhone() != null){
        currentCustomer.setPhone(customer.getPhone());
        }
        if(customer.getAgent() != null){
        currentCustomer.setAgent(agentServices.findAgentById(customer.getAgent().getAgentcode()));
        }

        if(customer.getOrders().size() > 0 ) {
            currentCustomer.getOrders().clear();
            for(Order o : customer.getOrders()) {
                Order newOrder = new Order();

                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setOrdamount(o.getAdvanceamount());
                newOrder.setOrderdescription(o.getOrderdescription());
                newOrder.setCustomer(currentCustomer);
                newOrder.setPayments(o.getPayments());

                currentCustomer.getOrders().add(newOrder);
            }
        }

        return custrepo.save(currentCustomer);
    }

}
