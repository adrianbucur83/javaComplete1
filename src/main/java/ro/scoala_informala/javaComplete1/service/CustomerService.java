package ro.scoala_informala.javaComplete1.service;

import org.springframework.stereotype.Service;
import ro.scoala_informala.javaComplete1.model.Customer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customerList =  new ArrayList<>();
    public CustomerService() {
        customerList.addAll(List.of(new Customer(15, "Popescu", null),
                new Customer(16, "toni", null)));
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    public void createCustomer(Customer customer) {
        customerList.add(customer);
    }

    public Customer getCustomerById(Integer id) {
       return customerList.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));
    }

    public void updateCustomer(Integer id, String newName){
        Customer customer = customerList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));
        customer.setName(newName);
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));
        customerList.remove(customer);
    }


}
