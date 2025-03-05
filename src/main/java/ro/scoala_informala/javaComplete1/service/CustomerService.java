package ro.scoala_informala.javaComplete1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.scoala_informala.javaComplete1.model.Customer;
import ro.scoala_informala.javaComplete1.model.dto.CustomerReturnDto;
import ro.scoala_informala.javaComplete1.model.dto.CustomerUpdateDto;
import ro.scoala_informala.javaComplete1.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerReturnDto> getAllCustomers() {
        List<Customer> customerFromDataBase = customerRepository.findAll();
        return customerFromDataBase.stream()
                .map(CustomerReturnDto::mapFromCustomer)
                .toList();
    }

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public CustomerReturnDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));
        return CustomerReturnDto.mapFromCustomer(customer);
    }

  public CustomerUpdateDto updateCustomer(Integer id, String newName, String newPhoneNumber) {
      return customerRepository.findById(id)
              .map(customer -> {
                  CustomerUpdateDto updatedDto = CustomerUpdateDto.updateCustomerDto(customer, newName, newPhoneNumber);
                  customer.setName(updatedDto.getName());
                  customer.setPhoneNumber(updatedDto.getPhoneNumber());
                  customerRepository.save(customer);
                  return updatedDto;
              })
              .orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));
  }


/*
    public void updateCustomer(Integer id, String newName) {
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Customer with id " + id + " does not exist"));
        customerRepository.save(customer);
    }

*/    public void deleteCustomer(Integer id) {
        getCustomerById(id); //verificam daca exista inainte sa il stergem
        customerRepository.deleteById(id);
    }
}
