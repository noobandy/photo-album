package in.anandm.pa.web.validators;

import in.anandm.pa.web.resources.Message;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MessageValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {

        return Message.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "message", "errors.required");
    }
}
