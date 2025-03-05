package ro.scoala_informala.javaComplete1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.scoala_informala.javaComplete1.model.Customer;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDto {
    private String name;
    private String phoneNumber;


    public static CustomerUpdateDto updateCustomerDto(Customer customer, String newName, String newPhoneNumber){
        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto();
        customerUpdateDto.setName(customer.getName());
        customerUpdateDto.setPhoneNumber(customer.getPhoneNumber());
        return customerUpdateDto;

    }
}
