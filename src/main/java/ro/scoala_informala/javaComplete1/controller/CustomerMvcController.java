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

@Controller
@RequestMapping("/mvc/customers")
@RequiredArgsConstructor
public class CustomerMvcController {

    private final CustomerService customerService;

    @PostMapping
    public String createCustomer(@ModelAttribute CustomerCreateDto customerCreateDto, Model model) {
        customerService.createCustomer(customerCreateDto.mapToCustomer());
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

    @GetMapping("/updateForm")
    public String displayUpdateForm(Model model, @RequestParam("Id") String customerId) {
        model.addAttribute("viewCustomerId", customerId);
        return "customers/updateCustomerForm";
    }

    @PostMapping("/update")
    public String updateCustomer(@PathVariable Integer id, @RequestBody CustomerUpdateDto customerUpdateDto) {
        customerService.updateCustomer(id, customerUpdateDto);
        return "redirect:/mvc/customers";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("id") Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/mvc/customers";
    }


}
