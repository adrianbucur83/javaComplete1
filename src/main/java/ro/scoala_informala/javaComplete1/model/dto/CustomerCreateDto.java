package ro.scoala_informala.javaComplete1.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.scoala_informala.javaComplete1.model.Customer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String name;

    @NotNull
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")

    private String phoneNumber;

    public Customer mapToCustomer() {
        Customer customer = new Customer();
        customer.setName(this.name);
        customer.setPhoneNumber(this.phoneNumber);
        return customer;
    }

}
