package lt.codeacademy.springmvc.services.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

@Target({ ElementType.FIELD})
@Constraint(validatedBy = { PhoneNumberValidator.class })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "{javax.validation.constraints.NotNull.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    PhoneNumberType phoneNumberType() default PhoneNumberType.FULL;

}
