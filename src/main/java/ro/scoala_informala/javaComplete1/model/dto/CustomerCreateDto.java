package ro.scoala_informala.javaComplete1.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.scoala_informala.javaComplete1.model.Customer;

@Getter
@Setter
@NoArgsConstructor
@Setter
public class CustomerCreateDto {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String name;
    @NotNull
    @Digits(integer = 10, fraction = 0)
    private String phoneNumber;
    private String prefix;


    public CustomerCreateDto(String name, String phoneNumber, String prefix) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.prefix = prefix;
    }

    public Customer mapToCustomer() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customer.setPrefix(prefix);
        return customer;
    }

}
