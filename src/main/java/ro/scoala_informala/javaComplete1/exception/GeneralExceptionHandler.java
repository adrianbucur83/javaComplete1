package ro.scoala_informala.javaComplete1.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleConflict(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        validationErrorResponse.setStatusCode(ex.getStatusCode().value());
        validationErrorResponse.setPath(request.getServletPath());
        validationErrorResponse.setMessage("Bad request, please correct the field values and try again");
        validationErrorResponse.setMethod(request.getMethod());

        List<ErrorInfo> errorList = ex.getFieldErrors().stream()
                .map(err -> {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setFieldName(err.getField());
                    errorInfo.setFieldValue(err.getRejectedValue().toString());
                    errorInfo.setDescription(err.getDefaultMessage());
                    return errorInfo;
                })
                .toList();
        validationErrorResponse.setErrorList(errorList);

        return new ResponseEntity(validationErrorResponse, ex.getStatusCode());
    }

//    @ExceptionHandler(value = BusinessException.class)


}
