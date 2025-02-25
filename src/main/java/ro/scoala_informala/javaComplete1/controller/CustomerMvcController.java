package ro.scoala_informala.javaComplete1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.scoala_informala.javaComplete1.model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mvc/customers")
public class CustomerMvcController {
    private List<Customer> customerList =  new ArrayList<>();

    public CustomerMvcController() {
        customerList.addAll(List.of(new Customer(15, "Popescu", null),
                new Customer(16, "toni", null)));
    }

    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        customerList.add(customer);
        //goes to the view
        model.addAttribute("customerList", customerList);
        model.addAttribute("date", LocalDate.now().toString());
        return "/customers/list";
    }

    @GetMapping()
    public String getAllCustomers(Model model) {
        model.addAttribute("customerList", customerList);
        model.addAttribute("date", LocalDate.now().toString());
        return "/customers/list";
    }

    @GetMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String  getCreateCustomerForm() {
        return "/customers/createCustomerForm";
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return customerList.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));
    }

    @GetMapping("/updateForm")
    public String displayUpdateForm(Model model, @RequestParam("Id") String customerId){
        model.addAttribute("viewCustomerId", customerId);
        return "customers/updateCustomerForm";

    }

    @PostMapping("/update")
    public String updateCustomer(@RequestParam("id") Integer id, @RequestParam String newName) {
        Customer customer = customerList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));

        customer.setName(newName);
        return "redirect:/mvc/customers";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("id") String id) {
        Customer customer = customerList.stream()
                .filter(c -> c.getId() == Integer.parseInt((id)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));

       customerList.remove(customer);
        return "redirect:/mvc/customers";
    }


}
