package lt.codeacademy.springmvc.services.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private PhoneNumberType phoneNumberType;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        this.phoneNumberType = constraintAnnotation.phoneNumberType();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (phoneNumberType.equals(PhoneNumberType.FULL)) {
            return isFullNumberValid(value);
        }

        return isPartialNumberValid(value);
    }

    private boolean isPartialNumberValid(String value) {
        if (value.length() != 9) {
            return false;
        }

        if (!value.substring(0,2).equals("86")){
            return false;
        }
        return true;
    }

    private boolean isFullNumberValid(String value) {
        if (value.length() != 12) {
            return false;
        }

        if(value.charAt(0) != '+') {
            return false;
        }

        if(!value.substring(1, 5).equals("3706")) {
            return false;
        }

        return true;
    }

}
