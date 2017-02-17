package by.grsu.entity.validator;

import by.grsu.entity.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ResourceValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return Resource.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "not nullable");
    }
}
