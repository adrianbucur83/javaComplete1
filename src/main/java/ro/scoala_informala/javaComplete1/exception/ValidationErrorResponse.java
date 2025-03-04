package ro.scoala_informala.javaComplete1.exception;

import lombok.Data;

import java.util.List;

@Data
public class ValidationErrorResponse {
    private int statusCode;
    private String path;
    private String message;
    private String method;
    private List<ErrorInfo> errorList;

}
