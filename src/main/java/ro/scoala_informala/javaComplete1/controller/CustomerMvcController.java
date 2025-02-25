package ro.scoala_informala.javaComplete1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.scoala_informala.javaComplete1.model.Customer;
import ro.scoala_informala.javaComplete1.service.CustomerService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mvc/customers")
@RequiredArgsConstructor
public class CustomerMvcController {

    private final CustomerService customerService;

    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.createCustomer(customer);
        model.addAttribute("customerList", customerService.getAllCustomers());
        model.addAttribute("date", LocalDate.now().toString());
        return "/customers/list";
    }

    @GetMapping()
    public String getAllCustomers(Model model) {
        model.addAttribute("customerList", customerService.getAllCustomers());
        model.addAttribute("date", LocalDate.now().toString());
        return "/customers/list";
    }

    @GetMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String getCreateCustomerForm() {
        return "/customers/createCustomerForm";
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/updateForm")
    public String displayUpdateForm(Model model, @RequestParam("Id") String customerId) {
        model.addAttribute("viewCustomerId", customerId);
        return "customers/updateCustomerForm";
    }

    @PostMapping("/update")
    public String updateCustomer(@RequestParam("id") Integer id, @RequestParam String newName) {
        customerService.updateCustomer(id, newName);
        return "redirect:/mvc/customers";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("id") Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/mvc/customers";
    }


}
