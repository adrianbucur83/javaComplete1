package ro.scoala_informala.javaComplete1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.scoala_informala.javaComplete1.model.Customer;
import ro.scoala_informala.javaComplete1.model.dto.CustomerCreateDto;
import ro.scoala_informala.javaComplete1.model.dto.CustomerUpdateDto;
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
    public String createCustomer(@ModelAttribute CustomerCreateDto customerCreateDto, Model model) {
        customerService.createCustomer(customerCreateDto.toCustomer());
        //goes to the viewcustomer
        model.addAttribute("customerList", customerService.getAllCustomers());
        model.addAttribute("date", LocalDate.now().toString());
        return "/customers/list"; //sau view customer created
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

    @GetMapping("/updateForm")
    public String displayUpdateForm(@RequestParam("Id") String customerId, Model model) {
        CustomerUpdateDto customerUpdateDto = customerService.updateCustomer(Integer.valueOf(customerId), null, null);
        model.addAttribute("customerUpdateDto", customerUpdateDto);
        model.addAttribute("viewCustomerId", customerId);
        return "/customers/updateCustomerForm";
    }

    //TO DO create a CustomerUpdateDTO with at least 2 updatable fields

    @PostMapping("/update")
    public String updateCustomer(@RequestParam("id") Integer id, @RequestParam String newName, @RequestParam String newPhoneNumber) {
        customerService.updateCustomer(id, newName, newPhoneNumber);
        return "redirect:/mvc/customers";
    }


    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("id") Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/mvc/customers";
    }
}
