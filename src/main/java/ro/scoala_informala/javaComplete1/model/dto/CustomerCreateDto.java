package ro.scoala_informala.javaComplete1.model.dto;

import ro.scoala_informala.javaComplete1.model.Customer;

public class CustomerCreateDto {
    private String name;
    private String phoneNumber;

    public CustomerCreateDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Customer mapToCustomer() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        return customer;
    }

}
