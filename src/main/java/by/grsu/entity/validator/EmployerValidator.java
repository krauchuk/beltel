package by.grsu.entity.validator;

import by.grsu.entity.Employer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployerValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return Employer.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "not nullable");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "not nullable");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middlename", "not nullable");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "centrix", "not nullable");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telJob", "not nullable");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "post_id", "not nullable");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zues_id", "not nullable");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sector_id", "not nullable");
    }
}
