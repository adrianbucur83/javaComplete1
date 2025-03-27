package ro.scoala_informala.javaComplete1.model.dto;

import lombok.Data;
import ro.scoala_informala.javaComplete1.model.Customer;
import ro.scoala_informala.javaComplete1.model.Order;

import java.util.List;

@Data
public class CustomerReturnDto {
    private int id;
    private String name;
    private String prefix;
    private String phoneNumber;
    private List<Order> orderList;

    public static CustomerReturnDto mapFromCustomer(Customer customer) {
        CustomerReturnDto customerReturnDto = new CustomerReturnDto();
        customerReturnDto.setId(customer.getId());
        customerReturnDto.setPrefix(customer.getPrefix());
        customerReturnDto.setName(customer.getName());
        customerReturnDto.setPhoneNumber(customer.getPhoneNumber());
        customerReturnDto.setOrderList(customer.getOrderList());
        return customerReturnDto;
    }
}
