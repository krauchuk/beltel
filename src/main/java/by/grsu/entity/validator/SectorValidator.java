package by.grsu.entity.validator;

import by.grsu.entity.Sector;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SectorValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return Sector.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "not nullable");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "division_id", "not nullable");
    }
}
