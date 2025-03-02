package ro.scoala_informala.javaComplete1.model.dto;

import lombok.Data;

@Data
public class CustomerUpdateDto {

    private int id;
    private String newName;
    private String newPhoneNumber;
}
