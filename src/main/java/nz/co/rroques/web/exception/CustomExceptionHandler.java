package nz.co.rroques.web.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@EnableWebMvc
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static class ErrorsView {

        @JsonProperty
        private final List<ErrorView> globalErrors = new ArrayList<>();

        @JsonProperty
        private final List<ErrorView> fieldErrors = new ArrayList<>();

        private ErrorsView(Errors errors) {
            errors.getGlobalErrors()
                    .stream()
                    .forEach( error -> this.globalErrors.add(ErrorView.createFrom(error)));
            errors.getFieldErrors()
                    .stream()
                    .forEach( error -> this.fieldErrors.add(ErrorView.createFrom(error)));
        }

        public static ErrorsView createFrom(Errors errors) {
            return new ErrorsView(errors);
        }

        private static class ErrorView {

            private final String code;
            private final String message;
            private final String field;

            private ErrorView(String code, String message, String field) {
                this.code = code;
                this.message = message;
                this.field = field;
            }

            private ErrorView(String code, String message) {
                this.code = code;
                this.message = message;
                this.field = "";
            }

            public static ErrorView createFrom(ObjectError objectError) {
                return new ErrorView(objectError.getCode(), objectError.getDefaultMessage());
            }

            public static ErrorView createFrom(FieldError fieldError) {
                return new ErrorView(fieldError.getCode(), fieldError.getDefaultMessage(), fieldError.getField());
            }
        }
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> processValidationError(ValidationException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorBody = asJSONString(ErrorsView.createFrom(ex.getErrors()));

        return handleExceptionInternal(ex, errorBody, headers, HttpStatus.BAD_REQUEST, request);
    }

    private String asJSONString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
