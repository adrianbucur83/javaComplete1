package ro.scoala_informala.javaComplete1.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ro.scoala_informala.javaComplete1.model.Customer;

@Setter
@Getter
@Data
public class CustomerUpdateDto {

    private String newName;

    public CustomerUpdateDto(String newName) {
        this.newName = newName;
    }

}