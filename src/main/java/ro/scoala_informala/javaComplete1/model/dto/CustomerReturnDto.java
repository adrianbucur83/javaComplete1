package ro.scoala_informala.javaComplete1.model.dto;

import lombok.Data;
import ro.scoala_informala.javaComplete1.model.Customer;

@Data
public class CustomerReturnDto {
    private int id;
    private String name;
    private String phoneNumber;

    public static CustomerReturnDto mapFromCustomer(Customer customer) {
        CustomerReturnDto customerReturnDto = new CustomerReturnDto();
        customerReturnDto.setId(customer.getId());
        customerReturnDto.setName(customer.getName());
        customerReturnDto.setPhoneNumber(customer.getPhoneNumber());
        return customerReturnDto;
    }
}
