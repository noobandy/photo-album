package in.anandm.pa.web.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Validator messageValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(messageValidator);

        webDataBinder.addCustomFormatter(new Formatter<ObjectId>() {

            @Override
            public String print(ObjectId object, Locale locale) {
                if (object != null) {
                    return object.toHexString();
                }
                else {
                    return "null";
                }

            }

            @Override
            public ObjectId parse(String text, Locale locale)
                    throws ParseException {
                if (text == null || text.isEmpty()) {
                    return null;
                }
                else {
                    if (ObjectId.isValid(text)) {
                        return new ObjectId(text);
                    }
                    else {
                        throw new ParseException(text, 0);
                    }

                }
            }
        });
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public @ResponseBody
    ResponseEntity<Map<String, Object>> handleDataBindingErrors(MethodArgumentNotValidException exception,
                                                                Locale locale) {
        BindingResult bindingResult = exception.getBindingResult();

        Object target = bindingResult.getTarget();

        List<String> globalErrorMessages = new ArrayList<String>();

        for (ObjectError objectError : bindingResult.getGlobalErrors()) {
            globalErrorMessages.add(messageSource.getMessage(
                    objectError.getCode(), objectError.getArguments(), locale));
        }

        Map<String, List<String>> fieldErrorMessages = new HashMap<String, List<String>>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            List<String> errorMessages = fieldErrorMessages.get(fieldError
                    .getField());

            if (errorMessages == null) {
                errorMessages = new ArrayList<String>();
            }

            errorMessages.add(messageSource.getMessage(
                    fieldError.getCode(), fieldError.getArguments(), locale));

            fieldErrorMessages.put(fieldError.getField(), errorMessages);
        }

        Map<String, Object> errorResult = new HashMap<String, Object>();
        errorResult.put("target", target);
        errorResult.put("errors", globalErrorMessages);
        errorResult.put("fieldErrors", fieldErrorMessages);

        return new ResponseEntity<Map<String, Object>>(errorResult,
                HttpStatus.BAD_REQUEST);
    }
}
