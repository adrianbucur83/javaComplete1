package ro.scoala_informala.javaComplete1.exception;

import lombok.Data;

@Data
public class ErrorInfo {
    private String fieldName;
    private String fieldValue;
    private String description;
}
