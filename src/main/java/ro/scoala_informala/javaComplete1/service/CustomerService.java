package ro.scoala_informala.javaComplete1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.scoala_informala.javaComplete1.exception.BusinessException;
import ro.scoala_informala.javaComplete1.model.Customer;
import ro.scoala_informala.javaComplete1.model.dto.CustomerReturnDto;
import ro.scoala_informala.javaComplete1.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerReturnDto> getAllCustomers() {
        List<Customer> customersFromDatabase = customerRepository.findAll();
        return customersFromDatabase.stream()
                .map(CustomerReturnDto::mapFromCustomer)
                .toList();
    }

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public CustomerReturnDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer with id " + id + " does not exist"));
        return CustomerReturnDto.mapFromCustomer(customer);
    }

    public void updateCustomer(Integer id, String newName) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer with id " + id + " does not exist"));
        customer.setName(newName);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        getCustomerById(id);
        customerRepository.deleteById(id);
    }


}
