package ro.scoala_informala.javaComplete1.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.scoala_informala.javaComplete1.model.Customer;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private List<Customer> customerList =  List.of(new Customer(15, "Popescu", null),
            new Customer(16, "toni", null));

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        // create in database the user
        return customer;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerList;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return customerList.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));
    }

    @PatchMapping("/{id}")
    public Customer updateCustomer(@PathVariable("id") Integer id, @RequestParam String newName) {
        Customer customer = customerList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));

        customer.setName(newName);
        return customer;
    }

    //TODO ADD DELETE CUSTOMER ENDPOINT



}
