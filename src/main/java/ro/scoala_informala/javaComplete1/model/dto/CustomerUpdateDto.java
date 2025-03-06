package ro.scoala_informala.javaComplete1.model.dto;

import lombok.Getter;
import lombok.Setter;
import ro.scoala_informala.javaComplete1.model.Customer;

@Setter
@Getter
public class CustomerUpdateDto {
	private String name;
	private String phoneNumber;

	public CustomerUpdateDto(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public static CustomerUpdateDto mapFromCustomer(Customer customer) {
		CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto(customer.getName(), customer.getPhoneNumber());
		return customerUpdateDto;
	}

}
