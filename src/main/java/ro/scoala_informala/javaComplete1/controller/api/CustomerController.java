package ro.scoala_informala.javaComplete1.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.scoala_informala.javaComplete1.model.dto.CustomerCreateDto;
import ro.scoala_informala.javaComplete1.model.dto.CustomerReturnDto;
import ro.scoala_informala.javaComplete1.model.dto.CustomerUpdateDto;
import ro.scoala_informala.javaComplete1.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED) //response status 201
    public void createCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
        // create in database the user
        customerService.createCustomer(customerCreateDto.mapToCustomer());
    }

    @GetMapping
    public List<CustomerReturnDto> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerReturnDto getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomerUpdateDto updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerUpdateDto dto) {
        CustomerUpdateDto updatedDto;
        updatedDto = customerService.updateCustomer(id, dto.getName(), dto.getPhoneNumber());
        return updatedDto;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
    }
}






