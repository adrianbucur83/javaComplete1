package ro.scoala_informala.javaComplete1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.scoala_informala.javaComplete1.model.Customer;
import ro.scoala_informala.javaComplete1.model.dto.CustomerCreateDto;
import ro.scoala_informala.javaComplete1.service.CustomerService;

import java.time.LocalDate;

@Controller
@RequestMapping("/mvc/customers")
@RequiredArgsConstructor
public class CustomerMvcController {

    private final CustomerService customerService;

    @GetMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String getCreateCustomerForm(Model model) {
        model.addAttribute("customerCreateDto", new CustomerCreateDto());
        return "/customers/createCustomerForm";
    }

    @PostMapping
    public String createCustomer(@ModelAttribute @Valid CustomerCreateDto customerCreateDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/customers/createCustomerForm";
        }
        customerService.createCustomer(customerCreateDto.mapToCustomer());
        model.addAttribute("customerList", customerService.getAllCustomers());
        model.addAttribute("date", LocalDate.now().toString());
        return "redirect:/mvc/customers";
    }

    @GetMapping()
    public String getAllCustomers(Model model) {
        model.addAttribute("customerList", customerService.getAllCustomers());
        model.addAttribute("date", LocalDate.now().toString());
        return "/customers/list";
    }

    @GetMapping("/updateForm")
    public String displayUpdateForm(Model model, @RequestParam("Id") String customerId) {
        model.addAttribute("viewCustomerId", customerId);
        return "customers/updateCustomerForm";
    }

    //TODO create a CustomerUpdateDto with at least 2 updatable field
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
