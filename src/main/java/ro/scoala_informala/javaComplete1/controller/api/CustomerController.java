package ro.scoala_informala.javaComplete1.controller.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.scoala_informala.javaComplete1.model.dto.CustomerCreateDto;
import ro.scoala_informala.javaComplete1.model.dto.CustomerReturnDto;
import ro.scoala_informala.javaComplete1.service.CustomerService;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCustomer(@RequestBody @Valid CustomerCreateDto customerCreateDto) {
        customerService.createCustomer(customerCreateDto.mapToCustomer());
    }

    @GetMapping
    public Page<CustomerReturnDto> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("/{id}")
    public CustomerReturnDto getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);
    }

    //TODO create a CustomerUpdateDto with at least 2 updatable field

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable("id") Integer id, @RequestParam String newName) {
       customerService.updateCustomer(id, newName);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
    }




}
