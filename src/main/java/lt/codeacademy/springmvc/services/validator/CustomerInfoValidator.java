package lt.codeacademy.springmvc.services.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lt.codeacademy.springmvc.entities.DeliveryInfo;

@Component
public class CustomerInfoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DeliveryInfo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "First name is required");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "Last name is required");
    }
}
